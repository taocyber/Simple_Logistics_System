<script setup>





</script>

<template>
  <!--  图片展示区域-->
  <div style="width: 100vw;height: 100vw;overflow: hidden;display: flex">

    <div style="flex: 1">

      <img  style="width: 100%;height: 100%;" fit ="cover"
            src="@/assets/Picture/login1.png" alt="自由才是这个世界的第一要义"/>
    </div>
    <div class="welcome-title">
      <div style="font-size: 30px;font-weight: bold">欢迎来到我们的世界</div>
      <div style="margin-top: 10px">111</div>
      <div style="margin-top: 10px">111</div>
    </div>


    <!--    登录区域界面设计-->
    <div style="width: 400px;background-color: #d7fafa">

      <router-view/>
    </div>

  </div>

</template>

<style scoped>

.welcome-title{
  position: absolute;
  bottom: 30px;
  left: 30px;
  color:white;text-shadow: 0 0 10px black;



}


</style>