<template>
    <div>
        <!-- Search Header -->
        <div class="bg-gradient-to-r from-blue-600 to-purple-600 py-8">
            <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
                <h1 class="text-white text-3xl font-bold mb-4">Tìm kiếm Tour</h1>
                <div class="bg-white rounded-lg shadow-lg p-4">
                    <div class="grid grid-cols-1 md:grid-cols-4 gap-4">
                        <div>
                            <label class="block text-sm font-medium text-gray-700 mb-1">Điểm đến</label>
                            <input v-model="keywords" type="text" id="searchDestination" placeholder="Nhập điểm đến..." class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent">
                        </div>
                        <div>
                            <label class="block text-sm font-medium text-gray-700 mb-1">Ngày khởi hành</label>
                            <input v-model="startDate" type="date" id="searchDate" class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent">
                        </div>
                        <div>
                            <label class="block text-sm font-medium text-gray-700 mb-1">Số người</label>
                            <input v-model="guests" type="number" id="searchPeople" min="1" value="1" class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent">
                        </div>
                        <div class="flex items-end">
                            <button @click="getTours()" id="searchBtn" class="w-full bg-blue-600 text-white px-6 py-2 rounded-lg hover:bg-blue-700 transition">
                                <i class="fas fa-search mr-2"></i>Tìm kiếm
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Main Content -->
        <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
            <div class="flex flex-col lg:flex-row gap-8">
                <!-- Sidebar Filters -->
                <div class="lg:w-1/4">
                    <div class="bg-white rounded-lg shadow-md p-6 sticky top-24">
                        <div class="flex justify-between items-center mb-4">
                            <h2 class="text-xl font-bold text-gray-800">Bộ lọc</h2>
                            <button id="clearFilters" class="text-sm text-blue-600 hover:text-blue-700">Xóa tất cả</button>
                        </div>

                        <!-- Price Range Filter -->
                        <div class="mb-6">
                            <button class="filter-toggle w-full flex justify-between items-center py-2 font-semibold text-gray-700" data-target="priceFilter">
                                <span>Giá tour</span>
                                <i class="fas fa-chevron-down"></i>
                            </button>
                            <div id="priceFilter" class="filter-section active mt-3">
                                <input type="range" id="priceRange" min="0" max="50000000" step="1000000" value="50000000" class="price-slider w-full h-2 bg-gray-200 rounded-lg appearance-none cursor-pointer">
                                <div class="flex justify-between mt-2 text-sm text-gray-600">
                                    <span>0đ</span>
                                    <span id="priceValue" class="font-semibold text-blue-600">50,000,000đ</span>
                                </div>
                            </div>
                        </div>

                        <!-- Duration Filter -->
                        <div class="mb-6 border-t pt-4">
                            <button class="filter-toggle w-full flex justify-between items-center py-2 font-semibold text-gray-700" data-target="durationFilter">
                                <span>Thời gian</span>
                                <i class="fas fa-chevron-down"></i>
                            </button>
                            <div id="durationFilter" class="filter-section active mt-3">
                                <label class="flex items-center mb-2 cursor-pointer">
                                    <input type="checkbox" class="duration-filter mr-2 w-4 h-4 text-blue-600 rounded" value="1-3">
                                    <span class="text-gray-700">1-3 ngày</span>
                                </label>
                                <label class="flex items-center mb-2 cursor-pointer">
                                    <input type="checkbox" class="duration-filter mr-2 w-4 h-4 text-blue-600 rounded" value="4-7">
                                    <span class="text-gray-700">4-7 ngày</span>
                                </label>
                                <label class="flex items-center mb-2 cursor-pointer">
                                    <input type="checkbox" class="duration-filter mr-2 w-4 h-4 text-blue-600 rounded" value="8-14">
                                    <span class="text-gray-700">8-14 ngày</span>
                                </label>
                                <label class="flex items-center cursor-pointer">
                                    <input type="checkbox" class="duration-filter mr-2 w-4 h-4 text-blue-600 rounded" value="15+">
                                    <span class="text-gray-700">Trên 15 ngày</span>
                                </label>
                            </div>
                        </div>

                        <!-- Category Filter -->
                        <div class="mb-6 border-t pt-4">
                            <button class="filter-toggle w-full flex justify-between items-center py-2 font-semibold text-gray-700" data-target="categoryFilter">
                                <span>Loại tour</span>
                                <i class="fas fa-chevron-down"></i>
                            </button>
                            <div id="categoryFilter" class="filter-section active mt-3">
                                <label class="flex items-center mb-2 cursor-pointer">
                                    <input type="checkbox" class="category-filter mr-2 w-4 h-4 text-blue-600 rounded" value="beach">
                                    <span class="text-gray-700">Biển đảo</span>
                                </label>
                                <label class="flex items-center mb-2 cursor-pointer">
                                    <input type="checkbox" class="category-filter mr-2 w-4 h-4 text-blue-600 rounded" value="mountain">
                                    <span class="text-gray-700">Núi rừng</span>
                                </label>
                                <label class="flex items-center mb-2 cursor-pointer">
                                    <input type="checkbox" class="category-filter mr-2 w-4 h-4 text-blue-600 rounded" value="city">
                                    <span class="text-gray-700">Thành phố</span>
                                </label>
                                <label class="flex items-center mb-2 cursor-pointer">
                                    <input type="checkbox" class="category-filter mr-2 w-4 h-4 text-blue-600 rounded" value="culture">
                                    <span class="text-gray-700">Văn hóa</span>
                                </label>
                                <label class="flex items-center cursor-pointer">
                                    <input type="checkbox" class="category-filter mr-2 w-4 h-4 text-blue-600 rounded" value="adventure">
                                    <span class="text-gray-700">Mạo hiểm</span>
                                </label>
                            </div>
                        </div>

                        <!-- Rating Filter -->
                        <div class="border-t pt-4">
                            <button class="filter-toggle w-full flex justify-between items-center py-2 font-semibold text-gray-700" data-target="ratingFilter">
                                <span>Đánh giá</span>
                                <i class="fas fa-chevron-down"></i>
                            </button>
                            <div id="ratingFilter" class="filter-section active mt-3">
                                <label class="flex items-center mb-2 cursor-pointer">
                                    <input type="radio" name="rating" class="rating-filter mr-2" value="5">
                                    <span class="text-yellow-400">★★★★★</span>
                                    <span class="text-gray-700 ml-2">(5 sao)</span>
                                </label>
                                <label class="flex items-center mb-2 cursor-pointer">
                                    <input type="radio" name="rating" class="rating-filter mr-2" value="4">
                                    <span class="text-yellow-400">★★★★☆</span>
                                    <span class="text-gray-700 ml-2">(4 sao trở lên)</span>
                                </label>
                                <label class="flex items-center mb-2 cursor-pointer">
                                    <input type="radio" name="rating" class="rating-filter mr-2" value="3">
                                    <span class="text-yellow-400">★★★☆☆</span>
                                    <span class="text-gray-700 ml-2">(3 sao trở lên)</span>
                                </label>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Results Section -->
                <div class="lg:w-3/4">
                    <!-- Results Header -->
                    <div class="bg-white rounded-lg shadow-md p-4 mb-6">
                        <div class="flex flex-col sm:flex-row justify-between items-start sm:items-center gap-4">
                            <div>
                                <h2 class="text-xl font-bold text-gray-800">Kết quả tìm kiếm</h2>
                                <p class="text-gray-600" id="resultCount">Tìm thấy {{ totalItems }} tour</p>
                            </div>
                            <div class="flex items-center gap-4">
                                <div class="flex items-center gap-2">
                                    <label class="text-sm text-gray-600">Sắp xếp:</label>
                                    <select id="sortSelect" class="border border-gray-300 rounded-lg px-3 py-2 text-sm focus:ring-2 focus:ring-blue-500 focus:border-transparent">
                                        <option value="popular">Phổ biến</option>
                                        <option value="price-asc">Giá tăng dần</option>
                                        <option value="price-desc">Giá giảm dần</option>
                                        <option value="rating">Đánh giá cao</option>
                                        <option value="newest">Mới nhất</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Tours Grid -->
                    <div id="toursContainer" class="grid grid-cols-1 md:grid-cols-2 gap-6">
                        <div v-for="tour in tours" :key="tour.id" class="tour-card bg-white rounded-lg shadow-md overflow-hidden cursor-pointer" @click="window.location.href=`tour-detail.html?id=${tour.id}`">
                            <img :src="tour.thumbnail" :alt="tour.title" class="w-full h-48 object-cover">
                            <div class="p-4">
                                <div class="flex items-center gap-2 mb-2">
                                    <span class="bg-blue-100 text-blue-600 text-xs px-2 py-1 rounded">{{ tour.duration_days }} ngày</span>
                                    <span class="bg-green-100 text-green-600 text-xs px-2 py-1 rounded">{{ tour.location }}</span>
                                </div>
                                <h3 class="text-lg font-bold text-gray-800 mb-2">{{ tour.title }}</h3>
                                <p class="text-gray-600 text-sm mb-3">{{ truncate(stripHtml(tour.description), 150) }}</p>
                                <div class="flex items-center mb-3">
                                    <span class="text-yellow-400">${stars}</span>
                                    <span class="text-gray-600 text-sm ml-2">${tour.rating} (${tour.reviews} đánh giá)</span>
                                </div>
                                <div class="flex justify-between items-center">
                                    <div>
                                        <span class="text-gray-500 text-sm">Giá từ</span>
                                        <p class="text-2xl font-bold text-blue-600">{{ tour.price.toLocaleString('vi-VN') }}đ</p>
                                    </div>
                                    <router-link :to="`/tours/${tour.id}`" class="bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700">
                                        Xem chi tiết
                                    </router-link>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Pagination -->
                    <div class="mt-8 flex justify-center">
                        <nav class="flex items-center gap-2">
                            <button id="prevPage" class="px-4 py-2 border border-gray-300 rounded-lg hover:bg-gray-50 disabled:opacity-50 disabled:cursor-not-allowed">
                                <i class="fas fa-chevron-left"></i>
                            </button>
                            <div id="pageNumbers" class="flex gap-2">
                                <!-- Page numbers will be inserted here -->
                            </div>
                            <button id="nextPage" class="px-4 py-2 border border-gray-300 rounded-lg hover:bg-gray-50 disabled:opacity-50 disabled:cursor-not-allowed">
                                <i class="fas fa-chevron-right"></i>
                            </button>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
    import { onMounted, ref, computed } from 'vue';
    import axios from "@/plugins/axios";
    import { useRoute } from 'vue-router';

    const tours = ref([]);
    const route = useRoute();
    const keywords = ref(route.query.keywords || '');
    const startDate = ref(route.query.startDate || '');
    const guests = ref(route.query.guests || 1);
    const totalItems = ref(0);

    const getTours = async () => {
        try {
            const response = await axios.get('/api/tours', {
                params: {
                    keywords: keywords.value,
                    startDate: startDate.value,
                    guests: guests.value
                }
            });
            tours.value = response.data.data.data;
            totalItems.value = response.data.data.totalItems;
        } catch (error) {
            console.error('Error fetching tours:', error);
        }
    };

    const stripHtml = (html = '') => {
        const div = document.createElement('div')
        div.innerHTML = html
        return div.textContent || div.innerText || ''
    }

    const truncate = (text = '', length = 150) => {
        return text.length > length
            ? text.slice(0, length) + '...'
            : text
    }


    onMounted(async () => {
        await getTours();
    });
</script>

<route lang="yaml">
meta:
    layout: Master
</route>