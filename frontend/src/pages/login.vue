<template>
  <div class="min-h-screen flex items-center justify-center px-4 py-12">
    <div class="max-w-md w-full">
        <!-- Logo -->
        <div class="text-center mb-8">
            <div class="flex items-center justify-center mb-4">
                <svg class="h-12 w-12 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z"></path>
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z"></path>
                </svg>
            </div>
            <h2 class="text-3xl font-bold text-gray-900">Đăng Nhập</h2>
            <p class="text-gray-600 mt-2">Chào mừng trở lại với SunBooking</p>
        </div>

        <!-- Login Form -->
        <div class="bg-white rounded-lg shadow-xl p-8">
             <!-- Error Message -->
            <div v-if="errorMessage" class="mb-4 p-4 bg-red-50 border border-red-200 text-red-600 rounded-lg">
                {{ errorMessage }}
            </div>
            <form id="login-form" class="space-y-6">
                <!-- Email -->
                <div>
                    <label for="email" class="block text-sm font-medium text-gray-700 mb-2">
                        Email
                    </label>
                    <input 
                        v-model="username"
                        type="email" 
                        id="email" 
                        name="email"
                        required
                        class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                        placeholder="email@example.com"
                    />
                </div>

                <!-- Password -->
                <div>
                    <label for="password" class="block text-sm font-medium text-gray-700 mb-2">
                        Mật khẩu
                    </label>
                    <div class="relative">
                        <input 
                            v-model="password"
                            type="password" 
                            id="password" 
                            name="password"
                            required
                            class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                            placeholder="••••••••"
                        />
                        <button 
                            type="button"
                            id="toggle-password"
                            class="absolute right-3 top-1/2 transform -translate-y-1/2 text-gray-500 hover:text-gray-700"
                        >
                            <svg id="eye-icon" class="h-5 w-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"></path>
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z"></path>
                            </svg>
                        </button>
                    </div>
                </div>

                <!-- Remember Me & Forgot Password -->
                <div class="flex items-center justify-between">
                    <div class="flex items-center">
                        <input 
                            type="checkbox" 
                            id="remember" 
                            name="remember"
                            class="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded"
                        />
                        <label for="remember" class="ml-2 block text-sm text-gray-700">
                            Ghi nhớ đăng nhập
                        </label>
                    </div>
                    <a href="#" class="text-sm text-blue-600 hover:text-blue-700">
                        Quên mật khẩu?
                    </a>
                </div>

                <!-- Submit Button -->
                <button 
                    type="button"
                    class="w-full bg-blue-600 text-white py-3 rounded-lg hover:bg-blue-700 transition font-semibold"
                    @click="login()"
                >
                    Đăng Nhập
                </button>
            </form>

            <!-- Divider -->
            <div class="relative my-6">
                <div class="absolute inset-0 flex items-center">
                    <div class="w-full border-t border-gray-300"></div>
                </div>
                <div class="relative flex justify-center text-sm">
                    <span class="px-2 bg-white text-gray-500">Hoặc đăng nhập với</span>
                </div>
            </div>

            <!-- Social Login -->
            <div class="grid grid-cols-1 gap-4">
                <GoogleLogin :callback="handleGoogleLogin" />
            </div>

            <!-- Sign Up Link -->
            <p class="text-center text-sm text-gray-600 mt-6">
                Chưa có tài khoản? 
                <router-link to="/register" class="text-blue-600 hover:text-blue-700 font-medium">
                    Đăng ký ngay
                </router-link>
            </p>
        </div>

        <!-- Back to Home -->
        <div class="text-center mt-6">
            <router-link to="/" class="text-sm text-gray-600 hover:text-gray-900">
                ← Quay lại trang chủ
            </router-link>
        </div>
    </div>
  </div>
</template>

<script setup>
    import { ref } from 'vue';
    import { useRouter } from 'vue-router';
    import axios from '@/plugins/axios';

    const router = useRouter();
    const username = ref('');
    const password = ref('');
    const errorMessage = ref('');
    const isLoading = ref(false);

    const login = async () => {
        
        errorMessage.value = '';
        isLoading.value = true;

        try {
            const response = await axios.post('/api/auth/login', {
                email: username.value,
                password: password.value
            });

            if (response.data.token) {
                localStorage.setItem('token', response.data.token);
            }

            window.location.href = '/';
        } catch (error) {
            console.error('Login failed:', error);
            errorMessage.value = error.response?.data?.message || 'Đăng nhập thất bại. Vui lòng thử lại!';
        } finally {
            isLoading.value = false;
        }
    };

    const handleGoogleLogin = async (response) => {
        try {
            const idToken = response.credential;

            const res = await axios.post('/api/auth/google', {
                idToken: idToken
            });
            const { token } = res.data;
            localStorage.setItem('token', token);
            
            window.location.href = '/';
        } catch (error) {
            errorMessage.value = error.response?.data?.message || 'Đăng nhập Google thất bại';
        }
    };

</script>

<route lang="yaml">
meta:
  layout: Auth
</route>
