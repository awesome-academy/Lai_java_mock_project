<template>
    <!-- Tour Images Gallery -->
    <section class="bg-white">
        <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
            <div class="grid grid-cols-1 md:grid-cols-4 gap-4">
                <template v-if="tour && tour.images && tour.images.length > 0">
                    <div 
                        v-for="(image, index) in tour.images.slice(0, 5)" 
                        :key="image.id"
                        :class="index === 0 ? 'md:col-span-2 md:row-span-2' : 'aspect-video md:aspect-auto'"
                        class="relative cursor-pointer group"
                        @click="openLightbox(index)"
                    >
                        <img 
                            :src="image.image_url"
                            :alt="`Tour Image ${index + 1}`"
                            class="w-full h-full object-cover rounded-lg transition-transform group-hover:scale-105"
                        />
                        <!-- Hover overlay -->
                        <div class="absolute inset-0 bg-black/0 group-hover:bg-black/20 transition-colors rounded-lg flex items-center justify-center">
                            <svg class="w-12 h-12 text-white opacity-0 group-hover:opacity-100 transition-opacity" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0zM10 7v3m0 0v3m0-3h3m-3 0H7"></path>
                            </svg>
                        </div>
                        <!-- Show remaining count on last image if more than 5 images -->
                        <button 
                            v-if="index === 4 && tour.images.length > 5"
                            class="absolute inset-0 bg-black/50 rounded-lg flex items-center justify-center text-white font-semibold hover:bg-black/60 transition"
                            @click.stop="openLightbox(4)"
                        >
                            +{{ tour.images.length - 5 }} ảnh
                        </button>
                    </div>
                </template>
                <!-- Fallback if no images -->
                <div v-else class="md:col-span-4 text-center py-8 text-gray-500">
                    Không có ảnh
                </div>
            </div>
        </div>
    </section>

    <!-- Tour Info -->
    <section class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        <div class="grid grid-cols-1 lg:grid-cols-3 gap-8">
            <!-- Left Column - Tour Details -->
            <div class="lg:col-span-2 space-y-6">
                <!-- Title & Rating -->
                <div>
                    <div class="flex items-center space-x-2 mb-2">
                        <span class="bg-yellow-100 text-yellow-800 px-3 py-1 rounded-full text-sm font-medium">
                            Nổi bật
                        </span>
                        <span class="text-gray-600">{{ tour?.location }}</span>
                    </div>
                    <h1 class="text-3xl font-bold text-gray-900 mb-3">
                        {{ tour?.title }}
                    </h1>
                    <div class="flex items-center space-x-4 text-sm">
                        <div class="flex items-center">
                            <svg class="h-5 w-5 text-yellow-500 fill-yellow-500" fill="currentColor" viewBox="0 0 20 20">
                                <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
                            </svg>
                            <span class="ml-1 font-semibold">4.9</span>
                            <span class="ml-1 text-gray-600">(128 đánh giá)</span>
                        </div>
                        <div class="flex items-center text-gray-600">
                            <svg class="h-5 w-5 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z"></path>
                            </svg>
                            {{ tour?.location }}
                        </div>
                    </div>
                </div>

                <!-- Description -->
                <div class="bg-white rounded-lg shadow-md p-6">
                    <div v-if="tour" class="prose prose-sm max-w-none text-gray-700 space-y-3" v-html="tour.description">
                        
                    </div>
                </div>

                <!-- Reviews Section -->
                <div class="bg-white rounded-lg shadow-md p-6" id="reviews">
                    <div class="flex items-center justify-between mb-6">
                        <h2 class="text-xl font-bold text-gray-900">Đánh Giá (128)</h2>
                        <button id="write-review-btn" class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition font-medium">
                            Viết đánh giá
                        </button>
                    </div>

                    <!-- Rating Summary -->
                    <div class="flex items-center space-x-8 mb-6 pb-6 border-b">
                        <div class="text-center">
                            <div class="text-5xl font-bold text-gray-900">4.9</div>
                            <div class="flex items-center justify-center mt-2">
                                <svg class="h-5 w-5 text-yellow-500 fill-yellow-500" fill="currentColor" viewBox="0 0 20 20">
                                    <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
                                </svg>
                                <svg class="h-5 w-5 text-yellow-500 fill-yellow-500" fill="currentColor" viewBox="0 0 20 20">
                                    <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
                                </svg>
                                <svg class="h-5 w-5 text-yellow-500 fill-yellow-500" fill="currentColor" viewBox="0 0 20 20">
                                    <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
                                </svg>
                                <svg class="h-5 w-5 text-yellow-500 fill-yellow-500" fill="currentColor" viewBox="0 0 20 20">
                                    <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
                                </svg>
                                <svg class="h-5 w-5 text-yellow-500 fill-yellow-500" fill="currentColor" viewBox="0 0 20 20">
                                    <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
                                </svg>
                            </div>
                        </div>
                        <div class="flex-1 space-y-2">
                            <div class="flex items-center space-x-2">
                                <span class="text-sm text-gray-600 w-8">5★</span>
                                <div class="flex-1 bg-gray-200 rounded-full h-2">
                                    <div class="bg-yellow-500 h-2 rounded-full" style="width: 85%"></div>
                                </div>
                                <span class="text-sm text-gray-600 w-8">109</span>
                            </div>
                            <div class="flex items-center space-x-2">
                                <span class="text-sm text-gray-600 w-8">4★</span>
                                <div class="flex-1 bg-gray-200 rounded-full h-2">
                                    <div class="bg-yellow-500 h-2 rounded-full" style="width: 10%"></div>
                                </div>
                                <span class="text-sm text-gray-600 w-8">13</span>
                            </div>
                            <div class="flex items-center space-x-2">
                                <span class="text-sm text-gray-600 w-8">3★</span>
                                <div class="flex-1 bg-gray-200 rounded-full h-2">
                                    <div class="bg-yellow-500 h-2 rounded-full" style="width: 3%"></div>
                                </div>
                                <span class="text-sm text-gray-600 w-8">4</span>
                            </div>
                            <div class="flex items-center space-x-2">
                                <span class="text-sm text-gray-600 w-8">2★</span>
                                <div class="flex-1 bg-gray-200 rounded-full h-2">
                                    <div class="bg-yellow-500 h-2 rounded-full" style="width: 1%"></div>
                                </div>
                                <span class="text-sm text-gray-600 w-8">1</span>
                            </div>
                            <div class="flex items-center space-x-2">
                                <span class="text-sm text-gray-600 w-8">1★</span>
                                <div class="flex-1 bg-gray-200 rounded-full h-2">
                                    <div class="bg-yellow-500 h-2 rounded-full" style="width: 1%"></div>
                                </div>
                                <span class="text-sm text-gray-600 w-8">1</span>
                            </div>
                        </div>
                    </div>

                    <!-- Individual Reviews -->
                    <div id="reviews-list" class="space-y-6">
                        <!-- Review 1 -->
                        <div class="border-b pb-6">
                            <div class="flex items-start space-x-4">
                                <img src="https://ui-avatars.com/api/?name=Nguyen+Van+A&background=3b82f6&color=fff" alt="User" class="w-12 h-12 rounded-full">
                                <div class="flex-1">
                                    <div class="flex items-center justify-between mb-2">
                                        <div>
                                            <h4 class="font-semibold text-gray-900">Nguyễn Văn A</h4>
                                            <p class="text-sm text-gray-500">2 ngày trước</p>
                                        </div>
                                        <div class="flex items-center">
                                            <svg class="h-4 w-4 text-yellow-500 fill-yellow-500" fill="currentColor" viewBox="0 0 20 20">
                                                <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
                                            </svg>
                                            <span class="ml-1 font-semibold">5.0</span>
                                        </div>
                                    </div>
                                    <p class="text-gray-700 mb-3">
                                        Chuyến đi tuyệt vời! Resort rất đẹp, nhân viên thân thiện. Các hoạt động lặn biển và ngắm san hô rất thú vị. 
                                        Gia đình tôi rất hài lòng và chắc chắn sẽ quay lại.
                                    </p>
                                    <div class="flex items-center space-x-4 text-sm">
                                        <button class="text-gray-500 hover:text-blue-600 flex items-center">
                                            <svg class="h-4 w-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M14 10h4.764a2 2 0 011.789 2.894l-3.5 7A2 2 0 0115.263 21h-4.017c-.163 0-.326-.02-.485-.06L7 20m7-10V5a2 2 0 00-2-2h-.095c-.5 0-.905.405-.905.905 0 .714-.211 1.412-.608 2.006L7 11v9m7-10h-2M7 20H5a2 2 0 01-2-2v-6a2 2 0 012-2h2.5"></path>
                                            </svg>
                                            Hữu ích (12)
                                        </button>
                                        <button class="text-gray-500 hover:text-blue-600 reply-btn">Trả lời</button>
                                    </div>
                                    
                                    <!-- Reply Form (hidden by default) -->
                                    <div class="reply-form hidden mt-4">
                                        <textarea 
                                            class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                                            rows="3"
                                            placeholder="Viết câu trả lời..."
                                        ></textarea>
                                        <div class="flex justify-end space-x-2 mt-2">
                                            <button class="cancel-reply px-4 py-2 text-gray-600 hover:bg-gray-100 rounded-lg transition">Hủy</button>
                                            <button class="submit-reply px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition">Gửi</button>
                                        </div>
                                    </div>

                                    <!-- Replies -->
                                    <div class="mt-4 space-y-4 ml-8">
                                        <div class="flex items-start space-x-3">
                                            <img src="https://ui-avatars.com/api/?name=Admin&background=10b981&color=fff" alt="Admin" class="w-8 h-8 rounded-full">
                                            <div class="flex-1">
                                                <div class="bg-gray-50 rounded-lg p-3">
                                                    <div class="flex items-center mb-1">
                                                        <h5 class="font-semibold text-sm text-gray-900">SunBooking</h5>
                                                        <span class="ml-2 text-xs bg-blue-100 text-blue-800 px-2 py-0.5 rounded">Admin</span>
                                                    </div>
                                                    <p class="text-sm text-gray-700">
                                                        Cảm ơn bạn đã lựa chọn SunBooking! Chúng tôi rất vui khi bạn hài lòng với chuyến đi.
                                                    </p>
                                                </div>
                                                <p class="text-xs text-gray-500 mt-1">1 ngày trước</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Review 2 -->
                        <div class="border-b pb-6">
                            <div class="flex items-start space-x-4">
                                <img src="https://ui-avatars.com/api/?name=Tran+Thi+B&background=f59e0b&color=fff" alt="User" class="w-12 h-12 rounded-full">
                                <div class="flex-1">
                                    <div class="flex items-center justify-between mb-2">
                                        <div>
                                            <h4 class="font-semibold text-gray-900">Trần Thị B</h4>
                                            <p class="text-sm text-gray-500">1 tuần trước</p>
                                        </div>
                                        <div class="flex items-center">
                                            <svg class="h-4 w-4 text-yellow-500 fill-yellow-500" fill="currentColor" viewBox="0 0 20 20">
                                                <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
                                            </svg>
                                            <span class="ml-1 font-semibold">5.0</span>
                                        </div>
                                    </div>
                                    <p class="text-gray-700 mb-3">
                                        Maldives đẹp không thể tả! Nước biển trong xanh, cát trắng mịn. Dịch vụ resort tuyệt vời, đồ ăn ngon. 
                                        Tour được tổ chức chuyên nghiệp.
                                    </p>
                                    <div class="flex items-center space-x-4 text-sm">
                                        <button class="text-gray-500 hover:text-blue-600 flex items-center">
                                            <svg class="h-4 w-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M14 10h4.764a2 2 0 011.789 2.894l-3.5 7A2 2 0 0115.263 21h-4.017c-.163 0-.326-.02-.485-.06L7 20m7-10V5a2 2 0 00-2-2h-.095c-.5 0-.905.405-.905.905 0 .714-.211 1.412-.608 2.006L7 11v9m7-10h-2M7 20H5a2 2 0 01-2-2v-6a2 2 0 012-2h2.5"></path>
                                            </svg>
                                            Hữu ích (8)
                                        </button>
                                        <button class="text-gray-500 hover:text-blue-600 reply-btn">Trả lời</button>
                                    </div>
                                    
                                    <!-- Reply Form (hidden by default) -->
                                    <div class="reply-form hidden mt-4">
                                        <textarea 
                                            class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                                            rows="3"
                                            placeholder="Viết câu trả lời..."
                                        ></textarea>
                                        <div class="flex justify-end space-x-2 mt-2">
                                            <button class="cancel-reply px-4 py-2 text-gray-600 hover:bg-gray-100 rounded-lg transition">Hủy</button>
                                            <button class="submit-reply px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition">Gửi</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <button class="w-full mt-6 py-3 border border-gray-300 rounded-lg text-gray-700 hover:bg-gray-50 transition font-medium">
                        Xem thêm đánh giá
                    </button>
                </div>
            </div>

            <!-- Right Column - Booking Card -->
            <div class="lg:col-span-1">
                <div class="bg-white rounded-lg shadow-xl p-6 sticky top-24">
                    <div class="mb-6">
                        <div class="flex items-baseline">
                            <span class="text-3xl font-bold text-blue-600">{{ new Intl.NumberFormat('vi-VN').format(tour?.price) }}đ</span>
                            <span class="ml-2 text-gray-600">/người</span>
                        </div>
                        <p class="text-sm text-gray-500 mt-1">Giá đã bao gồm thuế và phí</p>
                    </div>

                    <form id="booking-form" class="space-y-4">
                        <div>
                            <label class="block text-sm font-medium text-gray-700 mb-2">Ngày khởi hành</label>
                            <input 
                                type="date" 
                                class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                                required
                                v-model="startDate"
                            />
                        </div>

                        <div>
                            <label class="block text-sm font-medium text-gray-700 mb-2">Số người</label>
                            <div class="flex items-center space-x-4">
                                <button type="button" id="decrease-guests" class="w-10 h-10 border border-gray-300 rounded-lg hover:bg-gray-50" @click="numberOfGuests--">-</button>
                                <input
                                    v-model="numberOfGuests"
                                    type="number" 
                                    id="guests-input"
                                    value="2" 
                                    min="1"
                                    class="flex-1 text-center px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                                />
                                <button type="button" id="increase-guests" class="w-10 h-10 border border-gray-300 rounded-lg hover:bg-gray-50" @click="numberOfGuests++">+</button>
                            </div>
                        </div>

                        <div class="pt-4 border-t">
                            <div class="flex justify-between mb-2">
                                <span class="text-gray-600">{{ new Intl.NumberFormat('vi-VN').format(tour?.price) }}đ x <span id="guests-count">2</span> người</span>
                                <span class="font-semibold" id="subtotal">{{ new Intl.NumberFormat('vi-VN').format(tour?.price * 2) }}đ</span>
                            </div>
                            <div class="flex justify-between pt-2 border-t">
                                <span class="font-bold text-lg">Tổng cộng</span>
                                <span class="font-bold text-lg text-blue-600" id="total">{{ new Intl.NumberFormat('vi-VN').format(totalAmount()) }}đ</span>
                            </div>
                        </div>

                        <button 
                            type="button"
                            class="w-full bg-blue-600 text-white py-3 rounded-lg hover:bg-blue-700 transition font-semibold"
                            @click="handleBooking"
                        >
                            Đặt Tour Ngay
                        </button>
                    </form>

                    <p class="text-center text-sm text-gray-500 mt-4">
                        Bạn sẽ không bị trừ tiền ngay lập tức
                    </p>

                    <div class="mt-6 pt-6 border-t space-y-3">
                        <div class="flex items-center text-sm text-gray-600">
                            <svg class="h-5 w-5 mr-2 text-green-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
                            </svg>
                            Hoàn tiền 100% nếu hủy trước 7 ngày
                        </div>
                        <div class="flex items-center text-sm text-gray-600">
                            <svg class="h-5 w-5 mr-2 text-green-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
                            </svg>
                            Xác nhận đặt chỗ ngay lập tức
                        </div>
                        <div class="flex items-center text-sm text-gray-600">
                            <svg class="h-5 w-5 mr-2 text-green-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
                            </svg>
                            Hỗ trợ 24/7
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Review Modal -->
    <div id="review-modal" class="hidden fixed inset-0 bg-black/50 z-50 flex items-center justify-center p-4">
        <div class="bg-white rounded-lg max-w-md w-full p-6">
            <div class="flex items-center justify-between mb-4">
                <h3 class="text-xl font-bold text-gray-900">Viết Đánh Giá</h3>
                <button id="close-modal" class="text-gray-400 hover:text-gray-600">
                    <svg class="h-6 w-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
                    </svg>
                </button>
            </div>
            
            <form id="review-form" class="space-y-4">
                <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">Đánh giá của bạn</label>
                    <div class="flex space-x-2">
                        <button type="button" class="star-btn text-3xl text-gray-300 hover:text-yellow-500" data-rating="1">★</button>
                        <button type="button" class="star-btn text-3xl text-gray-300 hover:text-yellow-500" data-rating="2">★</button>
                        <button type="button" class="star-btn text-3xl text-gray-300 hover:text-yellow-500" data-rating="3">★</button>
                        <button type="button" class="star-btn text-3xl text-gray-300 hover:text-yellow-500" data-rating="4">★</button>
                        <button type="button" class="star-btn text-3xl text-gray-300 hover:text-yellow-500" data-rating="5">★</button>
                    </div>
                    <input type="hidden" id="rating-value" value="0" required>
                </div>

                <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">Nhận xét</label>
                    <textarea 
                        id="review-text"
                        rows="4"
                        required
                        class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                        placeholder="Chia sẻ trải nghiệm của bạn về tour này..."
                    ></textarea>
                </div>

                <button 
                    type="submit"
                    class="w-full bg-blue-600 text-white py-3 rounded-lg hover:bg-blue-700 transition font-semibold"
                >
                    Gửi Đánh Giá
                </button>
            </form>
        </div>
    </div>

    <!-- Image Lightbox Modal -->
    <Teleport to="body">
        <div 
            v-if="showLightbox" 
            class="fixed inset-0 bg-black/95 z-50 flex items-center justify-center"
            @click="closeLightbox"
        >
            <!-- Close button -->
            <button 
                @click="closeLightbox"
                class="absolute top-4 right-4 text-white hover:text-gray-300 transition z-10"
            >
                <svg class="w-8 h-8" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
                </svg>
            </button>

            <!-- Image counter -->
            <div class="absolute top-4 left-4 text-white text-lg font-semibold z-10">
                {{ currentImageIndex + 1 }} / {{ tour?.images?.length || 0 }}
            </div>

            <!-- Previous button -->
            <button 
                v-if="currentImageIndex > 0"
                @click.stop="previousImage"
                class="absolute left-4 text-white hover:text-gray-300 transition z-10"
            >
                <svg class="w-12 h-12" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7"></path>
                </svg>
            </button>

            <!-- Image -->
            <div class="max-w-7xl max-h-[90vh] px-16" @click.stop>
                <img 
                    v-if="tour?.images?.[currentImageIndex]"
                    :src="tour.images[currentImageIndex].image_url"
                    :alt="`Tour Image ${currentImageIndex + 1}`"
                    class="max-w-full max-h-[90vh] object-contain rounded-lg"
                />
            </div>

            <!-- Next button -->
            <button 
                v-if="currentImageIndex < (tour?.images?.length || 0) - 1"
                @click.stop="nextImage"
                class="absolute right-4 text-white hover:text-gray-300 transition z-10"
            >
                <svg class="w-12 h-12" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7"></path>
                </svg>
            </button>

            <!-- Thumbnail strip -->
            <div class="absolute bottom-4 left-1/2 transform -translate-x-1/2 flex gap-2 overflow-x-auto max-w-[90vw] px-4" @click.stop>
                <img 
                    v-for="(image, index) in tour?.images" 
                    :key="image.id"
                    :src="image.image_url"
                    :alt="`Thumbnail ${index + 1}`"
                    @click="currentImageIndex = index"
                    :class="[
                        'w-20 h-20 object-cover rounded cursor-pointer transition-all',
                        currentImageIndex === index ? 'ring-4 ring-white scale-110' : 'opacity-60 hover:opacity-100'
                    ]"
                />
            </div>
        </div>
    </Teleport>
</template>

<script setup>
    import { ref, computed, onMounted, onUnmounted, watch } from 'vue';
    import { useRouter } from 'vue-router';
    import axios from '@/plugins/axios';

    const router = useRouter();

    // Reactive state
    const guests = ref(2);
    const startDate = ref(new Date().toISOString().split('T')[0]);
    const tourId = ref(null);
    const tour = ref(null);

    // Lightbox state
    const showLightbox = ref(false);
    const currentImageIndex = ref(0);
    const numberOfGuests = ref(2);

    const getTourById = async () => {
        try {
            const response = await axios.get(`/api/tours/${tourId.value}`);
            tour.value = response.data.data;
        } catch (error) {
            console.error('Error fetching tour:', error);
        }
    };

    const totalAmount = () => {
        return tour.value?.price * guests.value;
    }

    // Lightbox methods
    const openLightbox = (index) => {
        currentImageIndex.value = index;
        showLightbox.value = true;
        document.body.style.overflow = 'hidden';
    };

    const closeLightbox = () => {
        showLightbox.value = false;
        document.body.style.overflow = '';
    };

    const nextImage = () => {
        if (currentImageIndex.value < (tour.value?.images?.length || 0) - 1) {
            currentImageIndex.value++;
        }
    };

    const previousImage = () => {
        if (currentImageIndex.value > 0) {
            currentImageIndex.value--;
        }
    };

    // Keyboard navigation
    const handleKeydown = (e) => {
        if (!showLightbox.value) return;
        
        if (e.key === 'Escape') closeLightbox();
        if (e.key === 'ArrowRight') nextImage();
        if (e.key === 'ArrowLeft') previousImage();
    };

    const handleBooking = () => {
        const params = {
            tour: tour.value,
            guests: guests.value,
            totalAmount: totalAmount(),
            startDate: startDate.value,
        }
        localStorage.setItem('booking', JSON.stringify(params));
        
        router.push('/tours/booking');
    }

    onMounted(() => {
        tourId.value = router.currentRoute.value.params.id;
        getTourById();
        window.addEventListener('keydown', handleKeydown);
    });

    watch(numberOfGuests, () => {
        guests.value = numberOfGuests.value;
    });

    // Cleanup
    onUnmounted(() => {
        window.removeEventListener('keydown', handleKeydown);
        document.body.style.overflow = '';
    });

</script>

<route lang="yaml">
meta:
    layout: Master
</route>