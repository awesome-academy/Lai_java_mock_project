<template>
    <div>
    <!-- Main Content -->
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        <div class="grid grid-cols-1 lg:grid-cols-3 gap-8">
            <!-- Left Column - Form -->
            <div class="lg:col-span-2">
                <!-- Tour Info Card -->
                <div class="bg-white rounded-lg shadow-md p-6 mb-6">
                    <h2 class="text-2xl font-bold text-gray-800 mb-4">Thông tin tour</h2>
                    <div class="flex flex-col sm:flex-row gap-4">
                        <img id="tourImage" :src="tour?.thumbnail" alt="Tour" class="w-full sm:w-32 h-32 object-cover rounded-lg">
                        <div class="flex-1">
                            <h3 id="tourName" class="text-xl font-bold text-gray-800 mb-2">{{ tour?.title}}</h3>
                            <div class="space-y-1 text-sm text-gray-600">
                                <p><i class="fas fa-clock mr-2 text-blue-600"></i><span id="tourDuration">{{ tour?.duration_days }} ngày {{ tour?.duration_days - 1 }} đêm</span></p>
                                <p><i class="fas fa-map-marker-alt mr-2 text-blue-600"></i><span id="tourDestination">{{ tour?.location }}</span></p>
                                <p><i class="fas fa-star mr-2 text-yellow-400"></i><span id="tourRating">4.8</span> (245 đánh giá)</p>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Booking Form -->
                <div class="bg-white rounded-lg shadow-md p-6 mb-6">
                    <h2 class="text-2xl font-bold text-gray-800 mb-6">Thông tin đặt tour</h2>
                    
                    <form id="bookingForm">
                        <!-- Departure Date -->
                        <div class="mb-6">
                            <label class="block text-sm font-semibold text-gray-700 mb-2">
                                Ngày khởi hành <span class="text-red-500">*</span>
                            </label>
                            <input type="date" v-model="formData.booking_date" id="departureDate" :class="{'border-red-500': errors.booking_date}" required class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent">
                            <p v-if="errors.booking_date" class="text-sm text-red-500 mt-1">{{ errors.booking_date }}</p>
                            <p v-else class="text-sm text-gray-500 mt-1">Vui lòng chọn ngày khởi hành trong tương lai</p>
                        </div>

                        <!-- Number of number_of_people -->
                        <div class="mb-6">
                            <label class="block text-sm font-semibold text-gray-700 mb-3">
                                Số lượng khách <span class="text-red-500">*</span>
                            </label>
                            <div class="space-y-4">
                                <input type="number" v-model="formData.number_of_people" id="number_of_people" :class="{'border-red-500': errors.number_of_people}" required class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent">
                                <p v-if="errors.number_of_people" class="text-sm text-red-500 mt-1">{{ errors.number_of_people }}</p>
                            </div>
                        </div>

                        <!-- Contact Information -->
                        <div class="mb-6">
                            <h3 class="text-lg font-semibold text-gray-800 mb-4">Thông tin liên hệ</h3>
                            <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
                                <div>
                                    <label class="block text-sm font-medium text-gray-700 mb-1">
                                        Họ và tên <span class="text-red-500">*</span>
                                    </label>
                                    <input type="text" v-model="formData.full_name" id="fullName" :class="{'border-red-500': errors.full_name}" required class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent" placeholder="Nguyễn Văn A">
                                    <p v-if="errors.full_name" class="text-sm text-red-500 mt-1">{{ errors.full_name }}</p>
                                </div>
                                <div>
                                    <label class="block text-sm font-medium text-gray-700 mb-1">
                                        Số điện thoại <span class="text-red-500">*</span>
                                    </label>
                                    <input type="tel" v-model="formData.phone" id="phone" :class="{'border-red-500': errors.phone}" required pattern="[0-9]{10}" class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent" placeholder="0912345678">
                                    <p v-if="errors.phone" class="text-sm text-red-500 mt-1">{{ errors.phone }}</p>
                                </div>
                                <div>
                                    <label class="block text-sm font-medium text-gray-700 mb-1">
                                        Email <span class="text-red-500">*</span>
                                    </label>
                                    <input type="email" v-model="formData.email" id="email" :class="{'border-red-500': errors.email}" required class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent" placeholder="example@email.com">
                                    <p v-if="errors.email" class="text-sm text-red-500 mt-1">{{ errors.email }}</p>
                                </div>
                                <div>
                                    <label class="block text-sm font-medium text-gray-700 mb-1">
                                        Địa chỉ <span class="text-red-500">*</span>
                                    </label>
                                    <input type="text" v-model="formData.address" id="address" :class="{'border-red-500': errors.address}" required class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent" placeholder="123 Đường ABC, Quận 1, TP.HCM">
                                    <p v-if="errors.address" class="text-sm text-red-500 mt-1">{{ errors.address }}</p>
                                </div>
                            </div>
                        </div>

                        <!-- Special Requests -->
                        <div class="mb-6">
                            <label class="block text-sm font-semibold text-gray-700 mb-2">
                                Yêu cầu đặc biệt
                            </label>
                            <textarea v-model="formData.note" id="specialRequests" rows="4" class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent" placeholder="Nhập yêu cầu đặc biệt của bạn (nếu có)..."></textarea>
                        </div>
                    </form>
                </div>
            </div>

            <!-- Right Column - Summary -->
            <div class="lg:col-span-1">
                <div class="bg-white rounded-lg shadow-md p-6 sticky top-24">
                    <h2 class="text-xl font-bold text-gray-800 mb-4">Tóm tắt đơn hàng</h2>
                    
                    <div class="space-y-3 mb-4 pb-4 border-b">
                        <div class="flex justify-between text-sm">
                            <span class="text-gray-600">Người lớn (×<span id="summaryAdults">{{ new Intl.NumberFormat('vi-VN').format(formData.number_of_people) }}</span>)</span>
                            <span class="font-semibold" id="summaryAdultsPrice">{{ new Intl.NumberFormat('vi-VN').format(formData.number_of_people * tour?.price) }}</span>
                        </div>
                        <div class="flex justify-between text-sm" id="summaryChildrenRow" style="display: none;">
                            <span class="text-gray-600">Trẻ em (×<span id="summaryChildren">0</span>)</span>
                            <span class="font-semibold" id="summaryChildrenPrice">0đ</span>
                        </div>
                        <div class="flex justify-between text-sm" id="summaryInfantsRow" style="display: none;">
                            <span class="text-gray-600">Em bé (×<span id="summaryInfants">0</span>)</span>
                            <span class="font-semibold" id="summaryInfantsPrice">0đ</span>
                        </div>
                    </div>

                    <div class="space-y-2 mb-4 pb-4 border-b">
                        <div class="flex justify-between text-sm">
                            <span class="text-gray-600">Tạm tính</span>
                            <span class="font-semibold" id="subtotal">{{ new Intl.NumberFormat('vi-VN').format(formData.number_of_people * tour?.price) }}</span>
                        </div>
                    </div>

                    <div class="flex justify-between items-center mb-6">
                        <span class="text-lg font-bold text-gray-800">Tổng cộng</span>
                        <span class="text-2xl font-bold text-blue-600" id="totalPrice">{{ new Intl.NumberFormat('vi-VN').format(formData.number_of_people * tour?.price) }}</span>
                    </div>

                    <button type="button" @click="handleBooking()" class="w-full bg-blue-600 text-white py-3 rounded-lg font-semibold hover:bg-blue-700 transition mb-3">
                        <i class="fas fa-check-circle mr-2"></i>Xác nhận đặt tour
                    </button>

                    <button type="button" onclick="window.history.back()" class="w-full border border-gray-300 text-gray-700 py-3 rounded-lg font-semibold hover:bg-gray-50 transition">
                        <i class="fas fa-arrow-left mr-2"></i>Quay lại
                    </button>

                    <div class="mt-6 p-4 bg-blue-50 rounded-lg">
                        <div class="flex items-start gap-3">
                            <i class="fas fa-info-circle text-blue-600 mt-1"></i>
                            <div class="text-sm text-gray-700">
                                <p class="font-semibold mb-1">Chính sách đặt tour</p>
                                <ul class="space-y-1 text-xs">
                                    <li>• Miễn phí hủy trước 7 ngày</li>
                                    <li>• Hoàn 50% nếu hủy trước 3 ngày</li>
                                    <li>• Không hoàn tiền nếu hủy trong 24h</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Success Modal -->
    <div id="successModal" class="hidden fixed inset-0 bg-black bg-opacity-50 z-50 flex items-center justify-center p-4">
        <div class="bg-white rounded-lg max-w-md w-full p-6 fade-in">
            <div class="text-center">
                <div class="w-16 h-16 bg-green-100 rounded-full flex items-center justify-center mx-auto mb-4">
                    <i class="fas fa-check-circle text-green-600 text-3xl"></i>
                </div>
                <h3 class="text-2xl font-bold text-gray-800 mb-2">Đặt tour thành công!</h3>
                <p class="text-gray-600 mb-6">Mã đặt tour của bạn là: <span class="font-bold text-blue-600" id="bookingCode">{{ tourCode }}</span></p>
                <p class="text-sm text-gray-600 mb-6">Chúng tôi đã gửi email xác nhận đến địa chỉ của bạn. Vui lòng kiểm tra hộp thư.</p>
                <div class="flex gap-3">
                    <button @click="router.push('/tours/histories')" class="flex-1 bg-blue-600 text-white py-3 rounded-lg font-semibold hover:bg-blue-700">
                        Xem lịch sử
                    </button>
                    <button @click="router.push('/')" class="flex-1 border border-gray-300 text-gray-700 py-3 rounded-lg font-semibold hover:bg-gray-50">
                        Về trang chủ
                    </button>
                </div>
            </div>
        </div>
    </div>
    </div>
</template>

<script setup>
    import { ref, onMounted } from 'vue'
    import axios from '@/plugins/axios'
    import { useRouter } from 'vue-router'

    const formData = ref({
        full_name: '',
        email: '',
        phone: '',
        address: '',
        note: '',
        tour_id: '',
        booking_date: '',
        end_date: '',
        number_of_people: 2,
    })
    const tourCode = ref('')
    const router = useRouter()
    const errors = ref({})

    const validate = () => {
        errors.value = {}
        let isValid = true

        if (!formData.value.full_name) {
            errors.value.full_name = 'Vui lòng nhập họ và tên'
            isValid = false
        }

        if (!formData.value.email) {
            errors.value.email = 'Vui lòng nhập email'
            isValid = false
        } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(formData.value.email)) {
             errors.value.email = 'Email không hợp lệ'
             isValid = false
        }

        if (!formData.value.phone) {
            errors.value.phone = 'Vui lòng nhập số điện thoại'
            isValid = false
        } else if (!/^[0-9]{10}$/.test(formData.value.phone)) {
            errors.value.phone = 'Số điện thoại phải có 10 chữ số'
            isValid = false
        }

        if (!formData.value.address) {
            errors.value.address = 'Vui lòng nhập địa chỉ'
            isValid = false
        }

        if (!formData.value.booking_date) {
            errors.value.booking_date = 'Vui lòng chọn ngày khởi hành'
            isValid = false
        }

        if (!formData.value.number_of_people || formData.value.number_of_people < 1) {
            errors.value.number_of_people = 'Số lượng khách không hợp lệ'
            isValid = false
        }

        return isValid
    }

    const tour = ref(null);

    const handleBooking = () => {
        if (!validate()) return;

        axios.post('/api/booking', formData.value)
            .then(response => {
                console.log(response);
                tourCode.value = response.data.data.id;
                document.getElementById('successModal').classList.remove('hidden');
                localStorage.removeItem('booking');
            })
            .catch(error => {
                console.error(error);
            })
    }

    onMounted(() => {
        const bookingData = JSON.parse(localStorage.getItem('booking'));
        if (bookingData) {
            tour.value = bookingData.tour;
            formData.value.tour_id = bookingData.tour.id;
            formData.value.booking_date = bookingData.startDate;
            formData.value.number_of_people = bookingData.guests;
        } else {
            window.location.href = '/tours';
        }
    });
</script>

<route lang="yaml">
meta:
    layout: Master
</route>

<style scoped>
    .step {
        transition: all 0.3s ease;
    }
    .step.active {
        display: block;
    }
    .step.inactive {
        display: none;
    }
    .progress-step {
        position: relative;
    }
    .progress-step::after {
        content: '';
        position: absolute;
        top: 50%;
        left: 50%;
        width: 100%;
        height: 2px;
        background: #e5e7eb;
        transform: translateY(-50%);
        z-index: -1;
    }
    .progress-step:last-child::after {
        display: none;
    }
    .progress-step.completed .step-circle {
        background: #3b82f6;
        color: white;
    }
    .progress-step.active .step-circle {
        border-color: #3b82f6;
        color: #3b82f6;
    }
    .addon-card {
        transition: all 0.3s ease;
    }
    .addon-card.selected {
        border-color: #3b82f6;
        background-color: #eff6ff;
    }
    .fade-in {
        animation: fadeIn 0.3s ease;
    }
    @keyframes fadeIn {
        from { opacity: 0; transform: translateY(10px); }
        to { opacity: 1; transform: translateY(0); }
    }
</style>