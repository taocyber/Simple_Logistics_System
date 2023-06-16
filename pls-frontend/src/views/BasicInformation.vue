<script setup>
import router from "@/router";
import {
  Avatar,
  Box,
  CreditCard,
  Goods,
  HomeFilled,
  Location,
  OfficeBuilding,
  Setting,
  Van
} from '@element-plus/icons-vue'






</script>

<template>
  <el-container class="layout-container-demo" style="height: 500px">

    <el-header >
      <div class="toolbar">

      </div>
    </el-header>
    <el-container>
      <el-aside style="width: 200px;color: #00FFFF">
        <el-scrollbar>
          <el-menu :default-openeds="['1', '9']">

            <el-menu-item index="1" @click="router.push('/information/PersonnelIM')">
              <template #title>
                <el-icon><Avatar /></el-icon>人员信息
              </template>
            </el-menu-item>

            <el-menu-item index="2" @click="router.push('/information/VehicleIM')">
              <template #title>
                <el-icon><Van /></el-icon>车辆信息
              </template>
            </el-menu-item>
            <el-menu-item index="3">
              <template #title>
                <el-icon><Location /></el-icon> 地址基本信息
              </template>
            </el-menu-item>

            <el-menu-item index="4">
              <template #title>
                <el-icon><OfficeBuilding /></el-icon>公司信息
              </template>
            </el-menu-item>

            <el-menu-item index="5">
              <template #title>
                <el-icon><Box /></el-icon>物资信息
              </template>
            </el-menu-item>

            <el-menu-item index="6">
              <template #title>
                <el-icon><CreditCard /></el-icon>证件基本信息
              </template>
            </el-menu-item>

            <el-menu-item index="7">
              <template #title>
                <el-icon><Goods /></el-icon>货物基本信息
              </template>
            </el-menu-item>

            <el-menu-item index="8">
              <template #title>
                <el-icon><HomeFilled /></el-icon>建筑构物信息
              </template>
            </el-menu-item>

            <el-menu-item index="9">
              <template #title>
                <el-icon><setting /></el-icon>机电设备信息
              </template>
            </el-menu-item>



          </el-menu>
        </el-scrollbar>
      </el-aside>

      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<style scoped>

.layout-container-demo .el-header {
  position: relative;
  height: 100px;
  background-color: var(--el-color-primary-light-7);
  color: var(--el-text-color-primary);
}
.layout-container-demo .el-aside {
  color: var(--el-text-color-primary);
  background: var(--el-color-primary-light-8);
}
.layout-container-demo .el-menu {
  border-right: none;
}



</style>