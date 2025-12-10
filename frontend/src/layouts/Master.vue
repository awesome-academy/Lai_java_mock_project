<template>
    <div>
        <Header />
        <router-view />
        <Footer />
    </div>
</template>

<script setup>
    import Header from './Header.vue';
    import Footer from './Footer.vue';
    import { ref, onMounted } from 'vue';
    import axios from '@/plugins/axios';
    
    const user = ref(null);

    const userDetail = async () => {
        try {
            const response = await axios.get('/api/auth/me');
            user.value = response.data.data;
            localStorage.setItem('user', JSON.stringify(user.value));
            localStorage.setItem('isLogin', true);
        } catch (error) {
            localStorage.removeItem('user');
            localStorage.removeItem('isLogin');
        }
    };

    onMounted(() => {
        userDetail();
    });
</script>