<script>
import axios from "axios";

export default {

  data() {
    return {
      services: []
    }
  },

  mounted() {
    axios.get('api/active/service').then(response => {
      this.services = response.data
      this.services.forEach(x => {
        let url = 'api/active/service/' + x.name
        axios.get(url).then(y => {
          let dns = y.data.dns
          if(dns === x.name || x.name === "homepage" || x.name === "auth-provider" || x.name === "push-button")
            x.url = 'https://' + dns + '.trevorism.com'
          else
            x.url = 'https://' + x.name + '.' + dns + '.trevorism.com'
        })
      })
    })
  }
}
</script>

<template>
  <div class="container">
    <div class="grid grid-cols-5 gap-4 tableHeader">
      <div class="tableCell">Name</div>
      <div class="tableCell col-span-2">Deploy URL</div>
      <div class="tableCell">Github</div>
      <div class="tableCellLast">Deploy Status</div>
    </div>
    <div v-for="item in services">
      <div class="grid grid-cols-5 gap-4">
        <div class="tableCell">{{ item.name }}</div>
        <div class="tableCell col-span-2"><va-chip flat v-bind:href="item.url"> {{item.url}}</va-chip></div>
        <div class="tableCell"><va-chip flat v-bind:href="item.github">{{ item.name }}</va-chip></div>
        <div class="tableCellLast"><img v-bind:src="item.statusUrl"/></div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.tableHeader {
  font-weight: bold;
}

.tableCell {
  border-right: 1px gray solid;
  border-bottom: 1px gray solid;
  white-space: nowrap;
  overflow: hidden;
  padding: 2px;
}

.tableCellLast {
  padding: 2px;
  border-bottom: 1px gray solid;
}

</style>
