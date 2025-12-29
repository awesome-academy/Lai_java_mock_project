package com.example.booking_tour.service;

import com.example.booking_tour.entity.Booking;
import com.example.booking_tour.entity.Tour;
import com.example.booking_tour.entity.User;
import com.example.booking_tour.repository.BookingRepository;
import com.example.booking_tour.repository.TourRepository;
import com.example.booking_tour.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelService {

    private final BookingRepository bookingRepository;
    private final TourRepository tourRepository;
    private final UserRepository userRepository;

    public ByteArrayInputStream exportBookingsToExcel(List<Booking> bookings) throws IOException {
        String[] columns = { "ID", "Full Name", "Phone", "Email", "Address", "Number of People", "Status",
                "Total Price", "Note", "Tour Title" };

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Bookings");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Create Header Row
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerCellStyle);
            }

            // Create Data Rows
            int rowIdx = 1;
            for (Booking booking : bookings) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(booking.getId());
                row.createCell(1).setCellValue(booking.getFull_name());
                row.createCell(2).setCellValue(booking.getPhone());
                row.createCell(3).setCellValue(booking.getEmail());
                row.createCell(4).setCellValue(booking.getAddress());
                row.createCell(5).setCellValue(booking.getNumber_of_people());
                row.createCell(6).setCellValue(booking.getStatus().toString());
                row.createCell(7).setCellValue(booking.getTotal_price().doubleValue());
                row.createCell(8).setCellValue(booking.getNote());
                row.createCell(9).setCellValue(booking.getTour() != null ? booking.getTour().getTitle() : "N/A");
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    public int importBookingsFromExcel(MultipartFile file) throws IOException {
        List<Booking> bookings = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            // Skip header row
            if (rows.hasNext()) {
                rows.next();
            }

            int rowNumCount = 1;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                rowNumCount++;
                final int rowNum = rowNumCount;

                Booking booking = new Booking();
                // Format: Col 0: Full Name, Col 1: Phone, Col 2: Email, Col 3: Address, Col 4:
                // Num People, Col 5: Total Price, Col 6: Tour ID, Col 7: User ID, Col 8: Note

                booking.setFull_name(getCellValueAsString(currentRow.getCell(0)));
                booking.setPhone(getCellValueAsString(currentRow.getCell(1)));
                booking.setEmail(getCellValueAsString(currentRow.getCell(2)));
                booking.setAddress(getCellValueAsString(currentRow.getCell(3)));

                Double numPeople = getCellValueAsNumeric(currentRow.getCell(4));
                booking.setNumber_of_people(numPeople != null ? numPeople.intValue() : 1);

                Double price = getCellValueAsNumeric(currentRow.getCell(5));
                booking.setTotal_price(price != null ? BigDecimal.valueOf(price) : BigDecimal.ZERO);

                // Kiểm tra Tour
                Double tourId = getCellValueAsNumeric(currentRow.getCell(6));
                if (tourId == null)
                    throw new RuntimeException("Dòng " + rowNum + ": Thiếu Tour ID");
                Tour tour = tourRepository.findById(tourId.intValue())
                        .orElseThrow(() -> new RuntimeException(
                                "Dòng " + rowNum + ": Tour ID " + tourId.intValue() + " không tồn tại"));
                booking.setTour(tour);

                // Kiểm tra User
                Double userId = getCellValueAsNumeric(currentRow.getCell(7));
                if (userId == null)
                    throw new RuntimeException("Dòng " + rowNum + ": Thiếu User ID");
                User user = userRepository.findById(userId.intValue())
                        .orElseThrow(() -> new RuntimeException(
                                "Dòng " + rowNum + ": User ID " + userId.intValue() + " không tồn tại"));
                booking.setUser(user);

                booking.setNote(getCellValueAsString(currentRow.getCell(8)));
                booking.setStatus(Booking.Status.PENDING);
                booking.setBooking_date(java.time.LocalDate.now().toString());

                bookings.add(booking);
            }
        }

        if (!bookings.isEmpty()) {
            bookingRepository.saveAll(bookings);
        }
        return bookings.size();
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null || cell.getCellType() == CellType.BLANK)
            return "";
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf((long) cell.getNumericCellValue());
        }
        return "";
    }

    private Double getCellValueAsNumeric(Cell cell) {
        if (cell == null || cell.getCellType() == CellType.BLANK)
            return null;
        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            try {
                return Double.parseDouble(cell.getStringCellValue());
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }
}
