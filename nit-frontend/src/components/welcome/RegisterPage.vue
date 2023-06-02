<script setup>
import {EditPen, Lock, Message, User} from "@element-plus/icons-vue";
import router from "@/router";
import {reactive} from "vue";


//表单内容绑定
const form =reactive( {
      username:'',
      password:'',
      password_verify:'',
      email:'',
      code:'',


})


</script>

<template>
<!--  注册界面设计-->

  <div style="text-align: center">
    <div style="margin-right:90px">
      <div style="font-size: 25px;color: white">注册新用户</div>
      <div style="font-size: 14px;color: white">欢迎加入我们，请在下方填写相关信息</div>
    </div>
  </div>
<!--注册基本信息区域-->
  <div style="text-align: center;margin-top:50px;
              margin-left: 90px; width: 300px">
<!--    用户名称-->
        <el-input v-model="form.username" type="text" placeholder="用户名">

          <template #prefix>
            <el-icon slot="prefix"><User /></el-icon>
          </template>

        </el-input>
<!--        密码设置-->
    <el-input v-model="form.password" type="password" style="margin-top: 20px" placeholder="密码">

      <template #prefix>
        <el-icon><Lock /></el-icon>
      </template>

    </el-input>
<!--            确认密码-->
    <el-input v-model="form.password_verify" type="password" style="margin-top: 20px" placeholder="密码确认">

      <template #prefix>
        <el-icon><Lock /></el-icon>
      </template>

    </el-input>
<!--    输入邮箱-->
    <el-input v-model="form.email" type="email" style="margin-top: 20px" placeholder="请输入电子邮箱">
      <template #prefix>
        <el-icon><Message /></el-icon>
      </template>
    </el-input>
  </div>
<!--  邮箱验证-->
  <div>
    <div  style="margin-top: 20px;margin-left: 90px">
        <el-row gutter="40" >
<!--          邮箱输入-->
            <el-col span="18" style="width: 180px">
                <el-input v-model="form.code" type="text" placeholder="请输入验证码">
                  <template #prefix>
                    <el-icon><EditPen /></el-icon>
                  </template>

                </el-input>

            </el-col>
<!--              ’点击认证‘按钮-->
            <el-col span="6" >
                <el-button type="success" style="width: 80px">获取验证码</el-button>

            </el-col>

        </el-row>

    </div>
  </div>

<!--  注册按钮-->
  <div style="margin-top: 50px;margin-right: 90px">
      <el-button style="width: 200px;color: #00FA9A" type="warning" plain>立即注册</el-button>
<!--实现返回登录界面-->
    <div style="margin-top: 5px">
      <el-link class="Earl_style" type="primary" @click="router.push('/')">已有账号？立即登录</el-link>
    </div>

  </div>



</template>

<style scoped>
/*已有账号，返回登录样式*/
.Earl_style{
  color: #666666;
}
.Earl_style:hover{
  color: #00FFFF;
}


</style>