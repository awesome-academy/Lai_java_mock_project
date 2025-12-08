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
    
</script>