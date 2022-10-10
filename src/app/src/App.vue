<script setup>
import SiteInfo from "./components/SiteInfo.vue";
import Webapps from "./components/Webapps.vue";
import Libraries from "./components/Libraries.vue";

</script>

<template>
  <div class="container">
    <h1>Active <a href="https://www.trevorism.com">Trevorism</a> Components</h1>
    <SiteInfo/>
    <h2>Active Web Applications</h2>
    <Webapps/>
    <h2>Active Libraries</h2>
    <Libraries/>
  </div>
</template>

<style scoped>
</style>
