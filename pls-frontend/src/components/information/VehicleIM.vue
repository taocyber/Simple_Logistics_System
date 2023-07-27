<script setup>

import {reactive} from "vue";

const tableDate = reactive([
  {
    licensePlate: '川A123461',
    brand:'红旗H5',
    VIN:'1HGCM82633A123456',
    age:'3',
    driver:'张三 ',

  },
])





</script>

<template>
<el-table :data="tableDate" height="600px" style="width: 100%">
  <el-table-column prop="licensePlate" label="车牌号" />
  <el-table-column prop="brand" label="品牌" />
  <el-table-column prop="VIN" label="识别码"/>
  <el-table-column prop="age" label="车龄"/>
  <el-table-column prop="driver" label="司机"/>
  <el-table-column fixed="right" label="操作" width="300">
    <template #default="{ row }">
      <el-button link type="primary" size="large" @click="showDetail(row)" round>查看</el-button>
      <el-button link type="warning" size="large" @click="redact(row)" round>编辑</el-button>
      <el-button link type="danger" size="large" @click="expurgate(row)" round>删除</el-button>
    </template>
    <template #header>
      <el-button type="primary" round style="float: right;margin-right: 20px"
                 @click="addVehiclel">新增</el-button>
    </template>
   </el-table-column>
</el-table>


</template>

<style scoped>

</style>