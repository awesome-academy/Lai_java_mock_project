<template>
    <router-view></router-view>
</template>

<script setup>
    import { useHead } from '@vueuse/head'

    useHead({
        title: 'Login',
        meta: [
            { name: 'description', content: 'Đăng nhập' },
        ],
    })
</script>

<style scoped></style>