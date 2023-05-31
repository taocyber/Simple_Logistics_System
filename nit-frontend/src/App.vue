<script setup >
import {User,Lock} from '@element-plus/icons-vue'



</script>



<template>
<!--  图片展示区域-->
  <div style="width: 100vw;height: 100vw;overflow: hidden;display: flex">

    <div style="flex: 1">

      <img  style="width: 100%;height: 100%;" fit ="cover"
            src="@/assets/Picture/login1.png" alt="自由才是这个世界的第一要义"/>

    </div>
<!--    登录区域界面设计-->
    <div style="width: 400px;background-color: #d7fafa">

      <div style="text-align: center; margin: 0 20px">
        <div style="margin-top:150px">
          <div style="font-size: 25px">登录</div>
          <div style="font-size: 14px;color: black">在进入系统前请先输入用户名和密码进行登录</div>
        </div>
<!--       登录行设计-->
        <div style="margin-top: 30px;font-size: 14px" >
<!--          用户账号/邮箱输入区-->
          <el-input type="text" placeholder="用户名/邮箱">
            <template #prefix>
              <el-icon slot="prefix"><User /></el-icon>
            </template>
          </el-input>
<!--          用户密码输入区-->
          <el-input type="password" style="margin-top: 4px" placeholder="密码">
            <template #prefix>
              <el-icon><Lock /></el-icon>
            </template>
          </el-input>
        </div>
<!--        小项功能：记住密码和账号，忘记密码连接-->
        <div >
<!--          自动记住账号密码-->
            <el-row style="margin-top: 5px;font-size:14px">
              <el-col :span="12" style="text-align: left">
                <el-checkbox v-model="checked1" label="记住我"/>
              </el-col>
<!--           忘记密码请求-->
              <el-col :span="12" style="text-align: right">
                <el-link>忘记密码</el-link>

              </el-col>
            </el-row>

        </div>
<!--        按钮区域-->
        <div style="margin-top: 40px" >
          <el-button style="width: 150px" type="success" color="#00FF7F" plain>立即登录</el-button>
        </div>
          <el-divider>
            <span style="color:#DC143C ">没有账号</span>
          </el-divider>

        <div>
          <el-button style="width: 150px" type="primary" color="#FF8C00" plain>注册账号</el-button>
        </div>


      </div>

    </div>

  </div>

</template>




<style scoped>





</style>
