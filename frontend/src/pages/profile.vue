<template>
    <!-- Profile Content -->
    <section v-if="user" class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        <div class="grid grid-cols-1 lg:grid-cols-4 gap-6">
            <!-- Sidebar -->
            <div class="lg:col-span-1">
                <div class="bg-white rounded-lg shadow-md p-6">
                    <div class="text-center mb-6">
                        <div class="relative inline-block">
                            <img 
                                id="profile-avatar"
                                :src="'https://ui-avatars.com/api/?name=' + user?.name + '&background=3b82f6&color=fff&size=128'"
                                alt="Profile"
                                class="w-24 h-24 rounded-full mx-auto mb-4"
                            />
                            <button class="absolute bottom-4 right-0 bg-blue-600 text-white p-2 rounded-full hover:bg-blue-700 transition">
                                <svg class="h-4 w-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 9a2 2 0 012-2h.93a2 2 0 001.664-.89l.812-1.22A2 2 0 0110.07 4h3.86a2 2 0 011.664.89l.812 1.22A2 2 0 0018.07 7H19a2 2 0 012 2v9a2 2 0 01-2 2H5a2 2 0 01-2-2V9z"></path>
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 13a3 3 0 11-6 0 3 3 0 016 0z"></path>
                                </svg>
                            </button>
                        </div>
                        <h2 class="text-xl font-bold text-gray-900">{{ user.name }}</h2>
                        <p class="text-gray-600">{{ user.email }}</p>
                    </div>

                    <nav class="space-y-2">
                        <button class="menu-item w-full text-left px-4 py-3 rounded-lg bg-blue-50 text-blue-600 font-medium" data-tab="personal">
                            <div class="flex items-center">
                                <svg class="h-5 w-5 mr-3" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z"></path>
                                </svg>
                                Thông tin cá nhân
                            </div>
                        </button>
                    </nav>
                </div>

                <!-- Stats Card -->
                <div class="bg-white rounded-lg shadow-md p-6 mt-6">
                    <h3 class="font-bold text-gray-900 mb-4">Thống kê</h3>
                    <div class="space-y-3">
                        <div class="flex justify-between items-center">
                            <span class="text-gray-600">Số tour đã đi</span>
                            <span class="font-bold text-blue-600">12</span>
                        </div>
                        <div class="flex justify-between items-center">
                            <span class="text-gray-600">Đánh giá</span>
                            <span class="font-bold text-blue-600">8</span>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Main Content -->
            <div class="lg:col-span-3">
                <!-- Personal Info Tab -->
                <div id="personal-tab" class="tab-content">
                    <div class="bg-white rounded-lg shadow-md p-6">
                        <h2 class="text-2xl font-bold text-gray-900 mb-6">Thông Tin Cá Nhân</h2>
                        
                        <form id="personal-form" class="space-y-6">
                            <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                                <div>
                                    <label class="block text-sm font-medium text-gray-700 mb-2">
                                        Họ và tên
                                    </label>
                                    <input
                                        v-model="user.name"
                                        type="text" 
                                        value="Nguyễn Văn A"
                                        class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                                    />
                                </div>

                                <div>
                                    <label class="block text-sm font-medium text-gray-700 mb-2">
                                        Email
                                    </label>
                                    <input 
                                        v-model="user.email"
                                        type="email" 
                                        value="nguyenvana@email.com"
                                        class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                                    />
                                </div>

                                <div>
                                    <label class="block text-sm font-medium text-gray-700 mb-2">
                                        Số điện thoại
                                    </label>
                                    <input 
                                        v-model="user.phone"
                                        type="tel" 
                                        value="0912345678"
                                        class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                                    />
                                </div>

                                <div>
                                    <label class="block text-sm font-medium text-gray-700 mb-2">
                                        Ngày sinh
                                    </label>
                                    <input 
                                        v-model="user.birthday"
                                        type="date" 
                                        value="1990-01-01"
                                        class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                                    />
                                </div>
                            </div>

                            <div>
                                <label class="block text-sm font-medium text-gray-700 mb-2">
                                    Địa chỉ
                                </label>
                                <textarea
                                    v-model="user.address"
                                    rows="3"
                                    class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                                >123 Đường ABC, Quận 1, TP. Hồ Chí Minh</textarea>
                            </div>

                            <div class="flex justify-end space-x-4">
                                <button 
                                    type="button"
                                    class="px-6 py-3 border border-gray-300 rounded-lg text-gray-700 hover:bg-gray-50 transition font-medium"
                                >
                                    Hủy
                                </button>
                                <button
                                    @click="updateProfile()"
                                    type="button"
                                    class="px-6 py-3 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition font-medium"
                                >
                                    Lưu thay đổi
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';
import { useHead } from '@vueuse/head';
import axios from "@/plugins/axios";

useHead({
    title: 'Trang cá nhân - SunBooking',
    meta: [
        {
            name: 'description',
            content: 'Quản lý thông tin cá nhân, bảo mật và tùy chọn của bạn trên SunBooking.'
        }
    ]
});
const router = useRouter();
const user = ref(null)

const customJs = () => {
    // Tab Navigation
    const menuItems = document.querySelectorAll('.menu-item');
    const tabContents = document.querySelectorAll('.tab-content');

    menuItems.forEach(item => {
        item.addEventListener('click', () => {
            const tabName = item.dataset.tab;
            
            // Update menu items
            menuItems.forEach(mi => {
                mi.classList.remove('bg-blue-50', 'text-blue-600');
                mi.classList.add('text-gray-700', 'hover:bg-gray-50');
            });
            item.classList.add('bg-blue-50', 'text-blue-600');
            item.classList.remove('text-gray-700', 'hover:bg-gray-50');
            
            // Update tab contents
            tabContents.forEach(tc => tc.classList.add('hidden'));
            document.getElementById(`${tabName}-tab`).classList.remove('hidden');
        });
    });
}

const updateProfile = async () => {
    try {
        const response = await axios.post('/api/auth/update-profile', user.value);
        if (response.data.success) {
            localStorage.setItem('user', JSON.stringify(response.data.data));
            user.value = response.data.data;
            alert('Cập nhật thông tin thành công!');
        } else {
            alert('Cập nhật thông tin thất bại: ' + response.data.message);
        }
    } catch (error) {
        alert('Lỗi khi cập nhật thông tin: ' + error.message);
    }
}

onMounted(() => {
    customJs();
    const userData = JSON.parse(localStorage.getItem('user'));
    if (userData) {
        user.value = userData;
    } else {
        router.push('/login');
        return;
    }
});
</script>

<route lang="yaml">
meta:
    layout: Master
</route>