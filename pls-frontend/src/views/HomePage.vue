<script  setup>
import { ref } from 'vue'
import {get} from "@/net";
import {ElMessage} from "element-plus";
import router from "@/router"

// 定义获取图片路径的方法，使用 new URL() 构造函数生成图片的绝对路径
const getImgUrl = (name) => {
  return new URL(`../assets/Picture/${name}`, import.meta.url).href
}
// 走马灯需要轮播的数据，是一个包含图片名的数组
const carouseData = [
  { url: "show1.png" },
  { url: "show2.png" },
  { url: "show3.png" },
]

const activeIndex = ref('1')
//退出登录

const logout =() =>{
  get('/api/auth/logout',(message) =>{
    ElMessage.success(message)
    router.push('/')
  })

}
//进入基本信息管理系统
// const basicinformation =() =>{
//   get('/api/auth/information',(message) =>{
//     ElMessage.success(message)
//     router.push('/information')
//
//   })




</script>




<template>

  <div class="outer">

    <!--    背景图-->
    <div class="slider" :style="{ backgroundImage: `url(${getImgUrl(carouseData[0].url)})` }">
    </div>
    <el-container >
      <el-header style="position: absolute; z-index: 2;margin-left: 80px ">


        <img src="../assets/Picture/logo2.png" alt="">
        <el-menu
            :default-active="activeIndex"
            class="el-menu-demo1"
            mode="horizontal"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b"
        >
          <el-sub-menu index="1" @click="router.push('/information')">
            <template #title>基本信息管理</template>
            <el-menu-item index="1-1" >人员信息</el-menu-item>
            <el-menu-item index="1-2">汽车信息</el-menu-item>
            <el-menu-item index="1-3">地址信息</el-menu-item>
          </el-sub-menu>

          <el-sub-menu index="2">
            <template #title>购销存管理</template>
            <el-menu-item index="2-1">物资购销存</el-menu-item>
            <el-menu-item index="2-2">汽车购销</el-menu-item>
            <el-menu-item index="2-3">货物购销存</el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="3">
            <template #title>汽车检修管理</template>
            <el-menu-item index="3-1">物资使用记录</el-menu-item>
            <el-menu-item index="3-2">修理工维修记录</el-menu-item>
            <el-menu-item index="3-3">汽车维修记录</el-menu-item>
          </el-sub-menu>

          <el-sub-menu index="4">
            <template #title>运输调度管理</template>
            <el-menu-item index="4-1">运输任务</el-menu-item>
            <el-menu-item index="4-2">承运商业务</el-menu-item>
            <el-menu-item index="4-3">铁路运输待办</el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="5">
            <template #title>对外合作管理</template>
            <el-menu-item index="5-1">公司简介公告</el-menu-item>
            <el-menu-item index="5-2">信息交流平台</el-menu-item>
            <el-menu-item index="5-3">销售广告管理</el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="6">
            <template #title>数据统计分析管理</template>
            <el-menu-item index="6-1">周期性报表</el-menu-item>
            <el-menu-item index="6-2">线性业务</el-menu-item>
            <el-menu-item index="6-3">业务状况分布</el-menu-item>

          </el-sub-menu>
          <el-sub-menu index="7">
            <template #title>个人中心</template>
            <el-menu-item index="6-1" @click="logout()">退出登录</el-menu-item>
          </el-sub-menu>
        </el-menu>

      </el-header>

      <el-main style="position: absolute; top: 320px; z-index: 2;">


        <el-space wrap style="margin: 0 auto">
          <el-card v-for="i in 4" :key="i" class="box-card" style="width: 500px; height: 300px">
            <template #header>
              <div class="card-header">
                <span>Card name</span>
                <el-button class="button" text>更多</el-button>
              </div>
            </template>
            <div v-for="o in 4" :key="o" class="text item">
              {{ 'List item ' + o }}
            </div>
          </el-card>
        </el-space>


      </el-main>

    </el-container>

  </div>







</template>



<style scoped>

.outer {
  width: 500px;
  height: 300px;
  overflow: scroll;
  padding-left: 150px;
}

.slider {
  position: fixed; /* 固定在页面 */
  top: 0;
  left: 0;
  right: 0;
  height: 100%; /* 设置高度 */
  z-index: 1;
  background-size: cover;
  background-position: center;
  animation: carousel 15s linear infinite;
}


@keyframes carousel {
  0% {
    background-image: url("../assets/Picture/show1.png");
  }
  50% {
    background-image: url("../assets/Picture/show2.png");
  }
  100% {
    background-image: url("../assets/Picture/show3.png");
  }
}

.el-menu-demo1{
  margin-top: 0;
}

.el-header img {
  z-index: 999;
  position: relative;
  width: 100%;

}

/*卡片设置 */
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.box-card {
  margin: 35px;
  border-radius: 4px;
  box-shadow: 0 2px 12px 0 rgba(0,0,0,.1);
}








</style>