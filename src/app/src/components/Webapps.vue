<script>
import axios from "axios";

export default {

  data () {
    return {
      services: []
    }
  },

  mounted() {
    axios.get('api/active/service').then(response =>
        this.services = response.data
    )
  }
}
</script>

<template>
  <div class="container">
    <van-row class="tableHeader">
      <van-col span="6" class="tableCell">Name</van-col>
      <van-col span="6" class="tableCell">Deploy URL </van-col>
      <van-col span="6" class="tableCell">Github </van-col>
      <van-col span="6" class="tableCellLast">Deploy Status </van-col>
    </van-row>
    <div v-for="item in services">
      <van-row>
        <van-col span="6" class="tableCell">{{ item.name }} </van-col>
        <van-col span="6" class="tableCell">{{ item.url }} </van-col>
        <van-col span="6" class="tableCell"><a href="{{ item.github }}">{{ item.name }}</a> </van-col>
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
  padding: 2px;
}
.tableCellLast{
  padding: 2px;
  border-bottom: 1px gray solid;
}

</style>
