<script setup></script>

<template>
  <div class="container hero">
    <div class="flex">
      <div class="w-1/4">Trevorism is hosted on</div>
      <div class="w-1/2"><va-chip flat href="https://console.cloud.google.com/">Google Cloud</va-chip></div>
    </div>
    <div class="flex">
      <div class="w-1/4">Trevorism is managed by</div>
      <div class="w-1/2"><va-chip flat href="https://admin.google.com/">Google Apps</va-chip></div>
    </div>
    <div class="flex">
      <div class="w-1/4">The codebase exists in</div>
      <div class="w-1/2"><va-chip flat href="https://github.com/trevorism">Trevorism Github</va-chip></div>
    </div>
    <div class="flex">
      <div class="w-1/4">The platform backlog is</div>
      <div class="w-1/2">
        <va-chip flat href="https://kanbanflow.com/board/a6a2c3aa67d9492ac64007975f9f322a">Kanban Flow</va-chip>
      </div>
    </div>
  </div>
</template>

<style scoped>
.hero {
  background-color: #bad2c7;
  border-radius: 15px;
  padding: 10px;
}
</style>
