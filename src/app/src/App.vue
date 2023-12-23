<script setup>
import MenuBar from '@trevorism/ui-header-bar'
import SiteInfo from './components/SiteInfo.vue'
import Webapps from './components/Webapps.vue'
import Libraries from './components/Libraries.vue'
</script>

<template>
  <menu-bar></menu-bar>
  <div class="ml-4">
    <h1 class="text-3xl font-bold ml-4 mt-2 mb-6">Active Trevorism Components</h1>
    <site-info></site-info>
    <h2 class="text-3xl font-bold ml-4 mt-2 mb-6">Active Web Applications</h2>
    <webapps></webapps>
    <h2 class="text-3xl font-bold ml-4 mt-2 mb-6">Active Libraries</h2>
    <libraries></libraries>
  </div>
</template>

<style scoped></style>
