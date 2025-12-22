<template>
    <section class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        <div class="mb-8">
            <h1 class="text-3xl font-bold text-gray-900 mb-2">Lịch Sử Đặt Tour</h1>
            <p class="text-gray-600">Quản lý và theo dõi các chuyến đi của bạn</p>
        </div>

        <!-- Filter Tabs -->
        <div class="bg-white rounded-lg shadow-md p-4 mb-6">
            <div class="flex flex-wrap gap-2">
                <button class="filter-tab px-4 py-2 rounded-lg bg-blue-600 text-white font-medium" data-status="all">
                    Tất cả
                </button>
                <button class="filter-tab px-4 py-2 rounded-lg bg-gray-100 text-gray-700 hover:bg-gray-200 font-medium" data-status="confirmed">
                    Đã xác nhận
                </button>
                <button class="filter-tab px-4 py-2 rounded-lg bg-gray-100 text-gray-700 hover:bg-gray-200 font-medium" data-status="completed">
                    Đã hoàn thành
                </button>
                <button class="filter-tab px-4 py-2 rounded-lg bg-gray-100 text-gray-700 hover:bg-gray-200 font-medium" data-status="cancelled">
                    Đã hủy
                </button>
            </div>
        </div>

        <!-- Booking List -->
        <div class="space-y-6">
            <!-- Booking Item 1 - Confirmed -->
            <div v-for="booking in bookings" :key="booking.id" class="booking-item bg-white rounded-lg shadow-md overflow-hidden" data-status="confirmed">
                <div class="p-6">
                    <div class="flex items-start justify-between mb-4">
                        <div class="flex-1">
                            <div class="flex items-center mb-2">
                                <span v-if="booking.status == 'CANCELLED'" class="bg-red-100 text-red-800 px-3 py-1 rounded-full text-sm font-medium">
                                    Đã hủy
                                </span>
                                <span v-else class="bg-green-100 text-green-800 px-3 py-1 rounded-full text-sm font-medium">
                                    Đã xác nhận
                                </span>
                                
                                <span class="ml-3 text-sm text-gray-600">Mã đơn: #{{ booking.id }}</span>
                            </div>
                            <h3 class="text-xl font-bold text-gray-900 mb-1">{{ booking.tour.title }}</h3>
                            <div class="flex items-center text-gray-600 text-sm">
                                <svg class="h-4 w-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z"></path>
                                </svg>
                                <span>{{ booking.booking_date }} - {{ getEndDate(booking.booking_date, booking.tour.duration_days) }}</span>
                                <span class="mx-2">•</span>
                                <span>{{ booking.number_of_people }} người</span>
                            </div>
                        </div>
                        <div v-if="booking.status == 'CANCELLED'" class="text-right">
                            <div class="text-2xl font-bold text-gray-400">{{ new Number(booking.total_price).toLocaleString() }}₫</div>
                            <div class="text-sm text-red-600">Đã hoàn tiền</div>
                        </div>
                        <div v-else class="text-right">
                            <div class="text-2xl font-bold text-blue-600">{{ new Number(booking.total_price).toLocaleString() }}₫</div>
                            <div class="text-sm text-gray-600">Đã thanh toán</div>
                        </div>
                    </div>

                    <div class="grid grid-cols-1 md:grid-cols-3 gap-4 py-4 border-t border-b">
                        <div>
                            <p class="text-sm text-gray-600 mb-1">Ngày đặt</p>
                            <p class="font-semibold text-gray-900">{{ booking.booking_date }}</p>
                        </div>
                        <div>
                            <p class="text-sm text-gray-600 mb-1">Phương thức thanh toán</p>
                            <p class="font-semibold text-gray-900">Chuyển khoản ngân hàng</p>
                        </div>
                        <div>
                            <p class="text-sm text-gray-600 mb-1">Trạng thái thanh toán</p>
                            <p class="font-semibold text-green-600">{{ booking.status == 'PENDING' ? 'Chưa thanh toán' : (booking.status == 'CONFIRMED' ? 'Đã thanh toán' : 'Đã hủy') }}</p>
                        </div>
                    </div>

                    <div class="flex flex-wrap gap-3 mt-4">
                        <router-link :to="`/tours/${booking.tour.id}`" class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition font-medium">
                            Xem chi tiết
                        </router-link>
                        <button class="px-4 py-2 border border-gray-300 rounded-lg text-gray-700 hover:bg-gray-50 transition font-medium">
                            Liên hệ hỗ trợ
                        </button>
                        <!-- <button class="px-4 py-2 border border-red-300 text-red-600 rounded-lg hover:bg-red-50 transition font-medium">
                            Hủy tour
                        </button> -->
                    </div>
                </div>
            </div>
        </div>
        <!-- Empty State -->
        <div id="empty-state" class="hidden bg-white rounded-lg shadow-md p-12 text-center">
            <svg class="h-24 w-24 mx-auto text-gray-400 mb-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z"></path>
            </svg>
            <h3 class="text-xl font-bold text-gray-900 mb-2">Không có đơn đặt tour</h3>
            <p class="text-gray-600 mb-6">Bạn chưa có đơn đặt tour nào trong mục này</p>
            <a href="index.html" class="inline-block px-6 py-3 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition font-medium">
                Khám phá tour
            </a>
        </div>
    </section>
</template>

<script setup>
    import { onMounted, ref } from 'vue';
    import axios from '@/plugins/axios';
    import { useRouter } from 'vue-router';

    const bookings = ref([]);
    const router = useRouter();

    const getHistories = async () => {
        try {
            const response = await axios.get('/api/booking/histories');
            if (response.data.success) {
                bookings.value = response.data.data;
            } else {
                bookings.value = [];
            }
        } catch (error) {
            console.error('Lỗi khi lấy lịch sử đặt tour:', error);
            bookings.value = [];
        }
    };

    const getEndDate = (startDate, durationDays) => {
        const start = new Date(startDate);
        start.setDate(start.getDate() + durationDays);
        return start.toISOString().split('T')[0];
    };

    onMounted(async () => {
        await getHistories();
    });
</script>

<route lang="yaml">
meta:
    layout: Master
</route>