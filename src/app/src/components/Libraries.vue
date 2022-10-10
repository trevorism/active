<script>
import axios from "axios";

export default {

  data() {
    return {
      libraries: []
    }
  },

  mounted() {
    axios.get('api/active/library').then(response =>
        this.libraries = response.data
    )
  }
}
</script>

<template>
  <div className="container">
    <van-row class="tableHeader">
      <van-col span="6" class="tableCell">Name</van-col>
      <van-col span="6" class="tableCell">Version</van-col>
      <van-col span="6" class="tableCell">Github</van-col>
      <van-col span="6" class="tableCellLast">Deploy Status</van-col>
    </van-row>
    <div v-for="item in libraries">
      <van-row>
        <van-col span="6" class="tableCell">{{ item.name }}</van-col>
        <van-col span="6" class="tableCell"><a v-bind:href="item.version">{{item.version}}</a></van-col>
        <van-col span="6" class="tableCell"><a href="{{ item.github }}">{{ item.name }}</a></van-col>
        <van-col span="6" class="tableCellLast"><img v-bind:src="item.statusUrl"/></van-col>
      </van-row>
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
