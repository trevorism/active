<script>
import axios from "axios";

export default {

  data() {
    return {
      libraries: []
    }
  },

  mounted() {
    axios.get('api/active/library').then(response => {
      this.libraries = response.data
      this.libraries.forEach(x => {
        let url = 'api/active/library/' + x.name + '/version'
        axios.get(url).then(y => {
          x.versionNumber = y.data
        })
      })
    })
  }
}
</script>

<template>
  <div className="container">
    <div class="grid grid-cols-4 gap-4 tableHeader">
      <div class="tableCell">Name</div>
      <div class="tableCell">Version</div>
      <div class="tableCell">Github</div>
      <div class="tableCellLast">Deploy Status</div>
    </div>
    <div v-for="item in libraries">
      <div class="grid grid-cols-4 gap-4">
        <div class="tableCell">{{ item.name }}</div>
        <div class="tableCell"><va-chip flat v-bind:href="item.version">{{item.versionNumber}}</va-chip></div>
        <div class="tableCell"><va-chip flat v-bind:href="item.github">{{ item.name }}</va-chip></div>
        <div class="tableCellLast"><img v-bind:src="item.statusUrl"/></div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.tableHeader{
  font-weight: bold;
}
.tableCell{
  border-right: 1px gray solid;
  border-bottom: 1px gray solid;
  white-space: nowrap;
  overflow: hidden;
  padding: 2px;
}
.tableCellLast{
  padding: 2px;
  border-bottom: 1px gray solid;
}
</style>
