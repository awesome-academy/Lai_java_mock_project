<template>
    <div>
        <!-- Hero Section -->
        <section id="home" class="relative h-[600px] flex items-center justify-center">
            <div class="absolute inset-0">
                <img 
                    src="https://images.unsplash.com/photo-1558117338-aa433feb1c62?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHx0cm9waWNhbCUyMGJlYWNoJTIwcmVzb3J0fGVufDF8fHx8MTc2NDgwMDk0NXww&ixlib=rb-4.1.0&q=80&w=1080"
                    alt="Hero background"
                    class="w-full h-full object-cover"
                />
                <div class="absolute inset-0 bg-black/40"></div>
            </div>
            
            <div class="relative z-10 text-center text-white max-w-4xl mx-auto px-4">
                <h1 class="text-4xl md:text-5xl lg:text-6xl font-bold mb-4">Khám Phá Thế Giới Cùng Chúng Tôi</h1>
                <p class="text-lg md:text-xl mb-8 text-white/90">Trải nghiệm những chuyến đi tuyệt vời với giá cả hợp lý và dịch vụ chuyên nghiệp</p>
                
                <!-- Search Form -->
                <form id="search-form" class="bg-white rounded-lg shadow-xl p-4 md:p-6 max-w-5xl mx-auto">
                    <div class="grid grid-cols-1 md:grid-cols-4 gap-4">
                        <div class="relative">
                            <svg class="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 h-5 w-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z"></path>
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z"></path>
                            </svg>
                            <input
                                type="text"
                                id="destination"
                                placeholder="Điểm đến"
                                class="w-full pl-10 pr-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent text-gray-900"
                            />
                        </div>
                        
                        <div class="relative">
                            <svg class="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 h-5 w-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z"></path>
                            </svg>
                            <input
                                type="date"
                                id="date"
                                class="w-full pl-10 pr-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent text-gray-900"
                            />
                        </div>
                        
                        <div class="relative">
                            <svg class="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 h-5 w-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4.354a4 4 0 110 5.292M15 21H3v-1a6 6 0 0112 0v1zm0 0h6v-1a6 6 0 00-9-5.197M13 7a4 4 0 11-8 0 4 4 0 018 0z"></path>
                            </svg>
                            <select
                                id="guests"
                                class="w-full pl-10 pr-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent appearance-none text-gray-900"
                            >
                                <option value="1">1 người</option>
                                <option value="2">2 người</option>
                                <option value="3">3 người</option>
                                <option value="4">4 người</option>
                                <option value="5+">5+ người</option>
                            </select>
                        </div>
                        
                        <button 
                            type="submit"
                            class="bg-blue-600 text-white px-6 py-3 rounded-lg hover:bg-blue-700 transition flex items-center justify-center space-x-2 font-medium"
                        >
                            <svg class="h-5 w-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path>
                            </svg>
                            <span>Tìm kiếm</span>
                        </button>
                    </div>
                </form>
            </div>
        </section>

        <!-- Destinations Section -->
        <section id="destinations" class="py-16 bg-gray-50">
            <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
                <div class="text-center mb-12">
                    <h2 class="text-3xl md:text-4xl font-bold mb-4">Điểm Đến Phổ Biến</h2>
                    <p class="text-gray-600 text-lg">Khám phá những địa điểm du lịch hàng đầu được yêu thích nhất</p>
                </div>
                
                <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
                    <!-- Destination Card 1 -->
                    <div class="group relative overflow-hidden rounded-xl shadow-lg hover:shadow-2xl transition-all duration-300 cursor-pointer">
                        <div class="aspect-[4/5] overflow-hidden">
                            <img 
                                src="https://images.unsplash.com/photo-1431274172761-fca41d930114?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxwYXJpcyUyMGVpZmZlbCUyMHRvd2VyfGVufDF8fHx8MTc2NDc2MDQxOHww&ixlib=rb-4.1.0&q=80&w=1080"
                                alt="Paris, Pháp"
                                class="w-full h-full object-cover group-hover:scale-110 transition-transform duration-300"
                            />
                        </div>
                        <div class="absolute inset-0 destination-gradient"></div>
                        <div class="absolute bottom-0 left-0 right-0 p-4 text-white">
                            <div class="flex items-center mb-2">
                                <svg class="h-4 w-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z"></path>
                                </svg>
                                <span class="font-semibold">Paris, Pháp</span>
                            </div>
                            <div class="flex justify-between items-center text-sm">
                                <span class="text-white/80">15 tours</span>
                                <span class="text-white/90 font-medium">Từ 25.000.000₫</span>
                            </div>
                        </div>
                    </div>

                    <!-- Destination Card 2 -->
                    <div class="group relative overflow-hidden rounded-xl shadow-lg hover:shadow-2xl transition-all duration-300 cursor-pointer">
                        <div class="aspect-[4/5] overflow-hidden">
                            <img 
                                src="https://images.unsplash.com/photo-1704253411612-e4deb715dcd8?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxiYWxpJTIwdGVtcGxlfGVufDF8fHx8MTc2NDcwODcxMXww&ixlib=rb-4.1.0&q=80&w=1080"
                                alt="Bali, Indonesia"
                                class="w-full h-full object-cover group-hover:scale-110 transition-transform duration-300"
                            />
                        </div>
                        <div class="absolute inset-0 destination-gradient"></div>
                        <div class="absolute bottom-0 left-0 right-0 p-4 text-white">
                            <div class="flex items-center mb-2">
                                <svg class="h-4 w-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z"></path>
                                </svg>
                                <span class="font-semibold">Bali, Indonesia</span>
                            </div>
                            <div class="flex justify-between items-center text-sm">
                                <span class="text-white/80">12 tours</span>
                                <span class="text-white/90 font-medium">Từ 15.000.000₫</span>
                            </div>
                        </div>
                    </div>

                    <!-- Destination Card 3 -->
                    <div class="group relative overflow-hidden rounded-xl shadow-lg hover:shadow-2xl transition-all duration-300 cursor-pointer">
                        <div class="aspect-[4/5] overflow-hidden">
                            <img 
                                src="https://images.unsplash.com/photo-1613395877344-13d4a8e0d49e?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxzYW50b3JpbmklMjBncmVlY2V8ZW58MXx8fHwxNzY0NzU0NDcwfDA&ixlib=rb-4.1.0&q=80&w=1080"
                                alt="Santorini, Hy Lạp"
                                class="w-full h-full object-cover group-hover:scale-110 transition-transform duration-300"
                            />
                        </div>
                        <div class="absolute inset-0 destination-gradient"></div>
                        <div class="absolute bottom-0 left-0 right-0 p-4 text-white">
                            <div class="flex items-center mb-2">
                                <svg class="h-4 w-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z"></path>
                                </svg>
                                <span class="font-semibold">Santorini, Hy Lạp</span>
                            </div>
                            <div class="flex justify-between items-center text-sm">
                                <span class="text-white/80">10 tours</span>
                                <span class="text-white/90 font-medium">Từ 30.000.000₫</span>
                            </div>
                        </div>
                    </div>

                    <!-- Destination Card 4 -->
                    <div class="group relative overflow-hidden rounded-xl shadow-lg hover:shadow-2xl transition-all duration-300 cursor-pointer">
                        <div class="aspect-[4/5] overflow-hidden">
                            <img 
                                src="https://images.unsplash.com/photo-1640871426525-a19540c45a39?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHx0b2t5byUyMGphcGFuJTIwY2l0eXxlbnwxfHx8fDE3NjQ3NDA2NDF8MA&ixlib=rb-4.1.0&q=80&w=1080"
                                alt="Tokyo, Nhật Bản"
                                class="w-full h-full object-cover group-hover:scale-110 transition-transform duration-300"
                            />
                        </div>
                        <div class="absolute inset-0 destination-gradient"></div>
                        <div class="absolute bottom-0 left-0 right-0 p-4 text-white">
                            <div class="flex items-center mb-2">
                                <svg class="h-4 w-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z"></path>
                                </svg>
                                <span class="font-semibold">Tokyo, Nhật Bản</span>
                            </div>
                            <div class="flex justify-between items-center text-sm">
                                <span class="text-white/80">18 tours</span>
                                <span class="text-white/90 font-medium">Từ 28.000.000₫</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- Tours Section -->
        <section id="tours" class="py-16 bg-white">
            <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
                <div class="text-center mb-12">
                    <h2 class="text-3xl md:text-4xl font-bold mb-4">Tour Nổi Bật</h2>
                    <p class="text-gray-600 text-lg">Những gói tour được săn đón nhất trong mùa này</p>
                </div>
                
                <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
                    <!-- Tour Card 1 -->
                    <div class="bg-white rounded-xl shadow-lg overflow-hidden hover:shadow-2xl transition-shadow duration-300 group">
                        <div class="relative aspect-[4/3] overflow-hidden">
                            <img 
                                src="https://images.unsplash.com/photo-1558117338-aa433feb1c62?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHx0cm9waWNhbCUyMGJlYWNoJTIwcmVzb3J0fGVufDF8fHx8MTc2NDgwMDk0NXww&ixlib=rb-4.1.0&q=80&w=1080"
                                alt="Du lịch biển Maldives"
                                class="w-full h-full object-cover group-hover:scale-110 transition-transform duration-300"
                            />
                            <div class="absolute top-4 right-4 bg-yellow-500 text-white px-3 py-1 rounded-full text-sm font-medium">
                                Nổi bật
                            </div>
                            <div class="absolute bottom-4 left-4 bg-white/90 backdrop-blur-sm px-3 py-1 rounded-lg text-gray-900 text-sm font-medium">
                                5 ngày 4 đêm
                            </div>
                        </div>
                        
                        <div class="p-4">
                            <h3 class="text-lg font-bold mb-2 line-clamp-2 text-gray-900">Du lịch biển Maldives 5N4Đ</h3>
                            
                            <div class="flex items-center text-gray-600 mb-3 text-sm">
                                <svg class="h-4 w-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z"></path>
                                </svg>
                                <span>Maldives</span>
                            </div>
                            
                            <div class="flex items-center justify-between mb-3">
                                <div class="flex items-center text-sm">
                                    <svg class="h-4 w-4 text-yellow-500 fill-yellow-500 mr-1" fill="currentColor" viewBox="0 0 20 20">
                                        <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
                                    </svg>
                                    <span class="font-semibold text-gray-900">4.9</span>
                                    <span class="text-gray-500 ml-1">(128 đánh giá)</span>
                                </div>
                            </div>
                            
                            <div class="flex items-center justify-between pt-3 border-t border-gray-200">
                                <div>
                                    <span class="text-gray-500 text-sm">Từ</span>
                                    <div class="text-blue-600 font-bold">35.000.000₫</div>
                                </div>
                                <button class="bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700 transition font-medium text-sm">
                                    Đặt ngay
                                </button>
                            </div>
                        </div>
                    </div>

                    <!-- Tour Card 2 -->
                    <div class="bg-white rounded-xl shadow-lg overflow-hidden hover:shadow-2xl transition-shadow duration-300 group">
                        <div class="relative aspect-[4/3] overflow-hidden">
                            <img 
                                src="https://images.unsplash.com/photo-1431274172761-fca41d930114?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxwYXJpcyUyMGVpZmZlbCUyMHRvd2VyfGVufDF8fHx8MTc2NDc2MDQxOHww&ixlib=rb-4.1.0&q=80&w=1080"
                                alt="Khám phá Châu Âu"
                                class="w-full h-full object-cover group-hover:scale-110 transition-transform duration-300"
                            />
                            <div class="absolute top-4 right-4 bg-yellow-500 text-white px-3 py-1 rounded-full text-sm font-medium">
                                Nổi bật
                            </div>
                            <div class="absolute bottom-4 left-4 bg-white/90 backdrop-blur-sm px-3 py-1 rounded-lg text-gray-900 text-sm font-medium">
                                7 ngày 6 đêm
                            </div>
                        </div>
                        
                        <div class="p-4">
                            <h3 class="text-lg font-bold mb-2 line-clamp-2 text-gray-900">Khám phá Châu Âu 7N6Đ</h3>
                            
                            <div class="flex items-center text-gray-600 mb-3 text-sm">
                                <svg class="h-4 w-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z"></path>
                                </svg>
                                <span>Pháp - Đức - Thụy Sĩ</span>
                            </div>
                            
                            <div class="flex items-center justify-between mb-3">
                                <div class="flex items-center text-sm">
                                    <svg class="h-4 w-4 text-yellow-500 fill-yellow-500 mr-1" fill="currentColor" viewBox="0 0 20 20">
                                        <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
                                    </svg>
                                    <span class="font-semibold text-gray-900">4.8</span>
                                    <span class="text-gray-500 ml-1">(95 đánh giá)</span>
                                </div>
                            </div>
                            
                            <div class="flex items-center justify-between pt-3 border-t border-gray-200">
                                <div>
                                    <span class="text-gray-500 text-sm">Từ</span>
                                    <div class="text-blue-600 font-bold">45.000.000₫</div>
                                </div>
                                <button class="bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700 transition font-medium text-sm">
                                    Đặt ngay
                                </button>
                            </div>
                        </div>
                    </div>

                    <!-- Tour Card 3 -->
                    <div class="bg-white rounded-xl shadow-lg overflow-hidden hover:shadow-2xl transition-shadow duration-300 group">
                        <div class="relative aspect-[4/3] overflow-hidden">
                            <img 
                                src="https://images.unsplash.com/photo-1704253411612-e4deb715dcd8?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxiYWxpJTIwdGVtcGxlfGVufDF8fHx8MTc2NDcwODcxMXww&ixlib=rb-4.1.0&q=80&w=1080"
                                alt="Phiêu lưu Bali"
                                class="w-full h-full object-cover group-hover:scale-110 transition-transform duration-300"
                            />
                            <div class="absolute bottom-4 left-4 bg-white/90 backdrop-blur-sm px-3 py-1 rounded-lg text-gray-900 text-sm font-medium">
                                6 ngày 5 đêm
                            </div>
                        </div>
                        
                        <div class="p-4">
                            <h3 class="text-lg font-bold mb-2 line-clamp-2 text-gray-900">Phiêu lưu Bali 6N5Đ</h3>
                            
                            <div class="flex items-center text-gray-600 mb-3 text-sm">
                                <svg class="h-4 w-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z"></path>
                                </svg>
                                <span>Bali, Indonesia</span>
                            </div>
                            
                            <div class="flex items-center justify-between mb-3">
                                <div class="flex items-center text-sm">
                                    <svg class="h-4 w-4 text-yellow-500 fill-yellow-500 mr-1" fill="currentColor" viewBox="0 0 20 20">
                                        <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
                                    </svg>
                                    <span class="font-semibold text-gray-900">4.7</span>
                                    <span class="text-gray-500 ml-1">(156 đánh giá)</span>
                                </div>
                            </div>
                            
                            <div class="flex items-center justify-between pt-3 border-t border-gray-200">
                                <div>
                                    <span class="text-gray-500 text-sm">Từ</span>
                                    <div class="text-blue-600 font-bold">18.000.000₫</div>
                                </div>
                                <button class="bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700 transition font-medium text-sm">
                                    Đặt ngay
                                </button>
                            </div>
                        </div>
                    </div>

                    <!-- Tour Card 4 -->
                    <div class="bg-white rounded-xl shadow-lg overflow-hidden hover:shadow-2xl transition-shadow duration-300 group">
                        <div class="relative aspect-[4/3] overflow-hidden">
                            <img 
                                src="https://images.unsplash.com/photo-1609373066983-cee8662ea93f?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxtb3VudGFpbiUyMGhpa2luZyUyMGFkdmVudHVyZXxlbnwxfHx8fDE3NjQ4MDExNDR8MA&ixlib=rb-4.1.0&q=80&w=1080"
                                alt="Chinh phục núi Alps"
                                class="w-full h-full object-cover group-hover:scale-110 transition-transform duration-300"
                            />
                            <div class="absolute bottom-4 left-4 bg-white/90 backdrop-blur-sm px-3 py-1 rounded-lg text-gray-900 text-sm font-medium">
                                8 ngày 7 đêm
                            </div>
                        </div>
                        
                        <div class="p-4">
                            <h3 class="text-lg font-bold mb-2 line-clamp-2 text-gray-900">Chinh phục núi Alps 8N7Đ</h3>
                            
                            <div class="flex items-center text-gray-600 mb-3 text-sm">
                                <svg class="h-4 w-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z"></path>
                                </svg>
                                <span>Thụy Sĩ</span>
                            </div>
                            
                            <div class="flex items-center justify-between mb-3">
                                <div class="flex items-center text-sm">
                                    <svg class="h-4 w-4 text-yellow-500 fill-yellow-500 mr-1" fill="currentColor" viewBox="0 0 20 20">
                                        <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
                                    </svg>
                                    <span class="font-semibold text-gray-900">4.9</span>
                                    <span class="text-gray-500 ml-1">(73 đánh giá)</span>
                                </div>
                            </div>
                            
                            <div class="flex items-center justify-between pt-3 border-t border-gray-200">
                                <div>
                                    <span class="text-gray-500 text-sm">Từ</span>
                                    <div class="text-blue-600 font-bold">52.000.000₫</div>
                                </div>
                                <button class="bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700 transition font-medium text-sm">
                                    Đặt ngay
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- Testimonials Section -->
        <section id="testimonials" class="py-16 bg-gray-50">
            <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
                <div class="text-center mb-12">
                    <h2 class="text-3xl md:text-4xl font-bold mb-4">Khách Hàng Nói Gì Về Chúng Tôi</h2>
                    <p class="text-gray-600 text-lg">Hàng nghìn khách hàng hài lòng với dịch vụ của chúng tôi</p>
                </div>
                
                <div class="grid grid-cols-1 md:grid-cols-3 gap-6">
                    <!-- Testimonial 1 -->
                    <div class="bg-white rounded-xl shadow-lg p-6 hover:shadow-xl transition-shadow duration-300">
                        <div class="flex items-center mb-4">
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
                        
                        <p class="text-gray-600 mb-6 italic">"Chuyến đi tuyệt vời! Dịch vụ chuyên nghiệp, hướng dẫn viên nhiệt tình. Gia đình tôi rất hài lòng."</p>
                        
                        <div class="flex items-center">
                            <img 
                                src="https://ui-avatars.com/api/?name=Nguyen+Thi+Mai&background=3b82f6&color=fff"
                                alt="Nguyễn Thị Mai"
                                class="w-12 h-12 rounded-full mr-4"
                            />
                            <div>
                                <div class="font-semibold text-gray-900">Nguyễn Thị Mai</div>
                                <div class="text-gray-500 text-sm">TP. Hồ Chí Minh</div>
                            </div>
                        </div>
                    </div>

                    <!-- Testimonial 2 -->
                    <div class="bg-white rounded-xl shadow-lg p-6 hover:shadow-xl transition-shadow duration-300">
                        <div class="flex items-center mb-4">
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
                        
                        <p class="text-gray-600 mb-6 italic">"Tour được tổ chức rất tốt, khách sạn đẹp, lịch trình hợp lý. Tôi sẽ quay lại lần nữa!"</p>
                        
                        <div class="flex items-center">
                            <img 
                                src="https://ui-avatars.com/api/?name=Tran+Van+Hung&background=10b981&color=fff"
                                alt="Trần Văn Hùng"
                                class="w-12 h-12 rounded-full mr-4"
                            />
                            <div>
                                <div class="font-semibold text-gray-900">Trần Văn Hùng</div>
                                <div class="text-gray-500 text-sm">Hà Nội</div>
                            </div>
                        </div>
                    </div>

                    <!-- Testimonial 3 -->
                    <div class="bg-white rounded-xl shadow-lg p-6 hover:shadow-xl transition-shadow duration-300">
                        <div class="flex items-center mb-4">
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
                        
                        <p class="text-gray-600 mb-6 italic">"Giá cả hợp lý, chất lượng tuyệt vời. Đội ngũ hỗ trợ rất tận tâm. Rất đáng để trải nghiệm!"</p>
                        
                        <div class="flex items-center">
                            <img 
                                src="https://ui-avatars.com/api/?name=Le+Thi+Huong&background=f59e0b&color=fff"
                                alt="Lê Thị Hương"
                                class="w-12 h-12 rounded-full mr-4"
                            />
                            <div>
                                <div class="font-semibold text-gray-900">Lê Thị Hương</div>
                                <div class="text-gray-500 text-sm">Đà Nẵng</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- CTA Section -->
        <section class="py-16 bg-blue-600 text-white">
            <div class="max-w-4xl mx-auto text-center px-4 sm:px-6 lg:px-8">
                <h2 class="text-3xl md:text-4xl font-bold mb-4 text-white">Sẵn Sàng Cho Chuyến Phiêu Lưu?</h2>
                <p class="text-lg mb-8 text-white/90">Đăng ký ngay hôm nay để nhận ưu đãi đặc biệt cho chuyến đi đầu tiên</p>
                <button class="bg-white text-blue-600 px-8 py-3 rounded-lg hover:bg-gray-100 transition font-semibold">
                    Đặt Tour Ngay
                </button>
            </div>
        </section>
    </div>
</template>

<script setup>
    import { onMounted } from 'vue';

    const customJs = () => {
        // Search Form Handler
        const searchForm = document.getElementById('search-form');
        searchForm.addEventListener('submit', (e) => {
            e.preventDefault();
            const destination = document.getElementById('destination').value;
            const date = document.getElementById('date').value;
            const guests = document.getElementById('guests').value;
            
            console.log('Tìm kiếm:', { destination, date, guests });
            alert(`Đang tìm kiếm tour đến ${destination || 'mọi điểm đến'} cho ${guests} với ngày khởi hành ${date || 'linh hoạt'}`);
        });

        // Smooth Scroll
        document.querySelectorAll('a[href^="#"]').forEach(anchor => {
            anchor.addEventListener('click', function (e) {
                e.preventDefault();
                const target = document.querySelector(this.getAttribute('href'));
                if (target) {
                    target.scrollIntoView({
                        behavior: 'smooth',
                        block: 'start'
                    });
                }
            });
        });

        // Book Tour Buttons
        const bookButtons = document.querySelectorAll('button:not(#mobile-menu-btn)');
        bookButtons.forEach(button => {
            if (button.textContent.includes('Đặt ngay') || button.textContent.includes('Đặt Tour Ngay')) {
                button.addEventListener('click', () => {
                    alert('Cảm ơn bạn đã quan tâm! Chức năng đặt tour sẽ sớm được cập nhật.');
                });
            }
        });
    };

    onMounted(() => {
        customJs();
    });
</script>


<route lang="yaml">
meta:
    layout: Master
</route>

<style scoped>
    .hero-gradient {
        background: linear-gradient(to top, rgba(0,0,0,0.7), rgba(0,0,0,0.2), transparent);
    }
    
    .destination-gradient {
        background: linear-gradient(to top, rgba(0,0,0,0.7) 0%, rgba(0,0,0,0.2) 50%, transparent 100%);
    }
    
    .smooth-scroll {
        scroll-behavior: smooth;
    }
    
    .line-clamp-2 {
        display: -webkit-box;
        -webkit-line-clamp: 2;
        -webkit-box-orient: vertical;
        overflow: hidden;
    }
</style>
