<script setup>





</script>

<template>
  <!--  图片展示区域-->
  <div class="login_background">

    <div class="welcome-title">
      <div style="font-size: 50px;font-weight: bold">欢迎来到我们的世界</div>
      <div style="margin-top: 20px">自由才是这个世界的第一要义</div>
      <div style="margin-top: 10px"></div>
    </div>

<!--登陆界面表单区域-->
    <div >
   <transition name="el-fade-in-linear">
     <router-view name="login"></router-view>
   </transition>

    </div>


    <!--  注册界面表单区域-->
    <div style="text-align: center; margin: 200px 20px 20px 950px">

    <transition name="el-fade-in-linear">
      <router-view name="register"></router-view>
    </transition>


    </div>



  </div>



</template>

<style scoped>

.welcome-title{
  position: absolute;
  bottom: 550px;
  left: 30px;
  color:white;text-shadow: 0 0 10px black;
}


.login_background{
  width:100%;
  height:100%;
  position:fixed;
  background-size:100% 100%;
  background-repeat: no-repeat;
  background-image: url("../assets/Picture/login1.png");


}

</style>