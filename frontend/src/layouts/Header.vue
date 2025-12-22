<template>
    <!-- Header -->
    <header class="bg-white shadow-sm sticky top-0 z-50">
        <nav class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
            <div class="flex justify-between items-center h-16">
                <router-link to="/" class="flex items-center">
                    <svg class="h-8 w-8 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z"></path>
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z"></path>
                    </svg>
                    <span class="ml-2 text-xl font-bold text-blue-600">SunBooking</span>
                </router-link>
                
                <!-- Desktop Menu -->
                <div class="hidden md:flex items-center space-x-8">
                    <router-link to="/" class="text-gray-700 hover:text-blue-600 transition">Trang chủ</router-link>
                    <!-- <router-link to="/destinations" class="text-gray-700 hover:text-blue-600 transition">Điểm đến</router-link> -->
                    <router-link to="/tours" class="text-gray-700 hover:text-blue-600 transition">Tour</router-link>
                    <!-- <router-link to="/testimonials" class="text-gray-700 hover:text-blue-600 transition">Đánh giá</router-link> -->
                    <!-- <router-link to="/contact" class="text-gray-700 hover:text-blue-600 transition">Liên hệ</router-link> -->
                </div>

                <div v-if="!isLogin" class="hidden md:flex items-center space-x-4">
                    <router-link to="/login" class="px-4 py-2 text-blue-600 hover:bg-blue-50 rounded-lg transition font-medium">
                        Đăng nhập
                    </router-link>
                    <router-link to="/register" class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition font-medium">
                        Đăng ký
                    </router-link>
                </div>
                <div v-else class="hidden md:flex items-center space-x-4">
                    <router-link to="/profile" class="px-4 py-2 text-blue-600 hover:bg-blue-50 rounded-lg transition font-medium">
                        Trang cá nhân
                    </router-link>
                    <button @click="handleLogout()" class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition font-medium">
                        Đăng xuất
                    </button>
                </div>

                <!-- Mobile Menu Button -->
                <button id="mobile-menu-btn" class="md:hidden">
                    <svg id="menu-icon" class="h-6 w-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16M4 18h16"></path>
                    </svg>
                    <svg id="close-icon" class="h-6 w-6 hidden" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
                    </svg>
                </button>
            </div>

            <!-- Mobile Menu -->
            <div id="mobile-menu" class="hidden md:hidden py-4 border-t">
                <div class="flex flex-col space-y-4">
                    <a href="#home" class="text-gray-700 hover:text-blue-600 transition">Trang chủ</a>
                    <a href="#destinations" class="text-gray-700 hover:text-blue-600 transition">Điểm đến</a>
                    <a href="#tours" class="text-gray-700 hover:text-blue-600 transition">Tour</a>
                    <a href="#testimonials" class="text-gray-700 hover:text-blue-600 transition">Đánh giá</a>
                    <a href="#contact" class="text-gray-700 hover:text-blue-600 transition">Liên hệ</a>
                    <div class="flex flex-col space-y-2 pt-4">
                        <router-link to="/login" class="px-4 py-2 text-blue-600 hover:bg-blue-50 rounded-lg transition font-medium">
                            Đăng nhập
                        </router-link>
                        <router-link to="/register" class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition font-medium">
                            Đăng ký
                        </router-link>
                    </div>
                </div>
            </div>
        </nav>
    </header>
</template>

<script setup>
    import { onMounted, ref } from 'vue';

    const isLogin = ref(false);
    const user = ref(null);

    const customJs = () => {
        // Mobile Menu Toggle
        const mobileMenuBtn = document.getElementById('mobile-menu-btn');
        const mobileMenu = document.getElementById('mobile-menu');
        const menuIcon = document.getElementById('menu-icon');
        const closeIcon = document.getElementById('close-icon');

        if (!mobileMenuBtn || !mobileMenu || !menuIcon || !closeIcon) {
            return;
        }
        mobileMenuBtn.addEventListener('click', () => {
            mobileMenu.classList.toggle('hidden');
            menuIcon.classList.toggle('hidden');
            closeIcon.classList.toggle('hidden');
        });

        // Close mobile menu when clicking on a link
        const mobileMenuLinks = mobileMenu.querySelectorAll('a');
        mobileMenuLinks.forEach(link => {
            link.addEventListener('click', () => {
                mobileMenu.classList.add('hidden');
                menuIcon.classList.remove('hidden');
                closeIcon.classList.add('hidden');
            });
        });

        isLogin.value = localStorage.getItem('isLogin');
        user.value = localStorage.getItem('user');
    };

    const handleLogout = () => {
        localStorage.removeItem('isLogin');
        localStorage.removeItem('user');
        window.location.href = '/login';
    };

    onMounted(() => {
        customJs();
    });
</script>