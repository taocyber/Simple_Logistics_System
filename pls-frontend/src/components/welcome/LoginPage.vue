<script setup>
import {Lock, User} from "@element-plus/icons-vue";
import {reactive} from "vue";
import {ElMessage} from "element-plus";
import {post} from "@/net";
import router from "@/router";


//登录用户名和密码，多选框反应式
const form = reactive({
  username:'',
  password:'',
  remember: false

})
//判断来自登录界面的请求
const login = () => {
  if (!form.username || !form.password) {
    ElMessage.warning('请填写用户名和密码')
  } else {
    post('/api/auth/login', {

      username: form.username,
      password: form.password,
      remember: form.remember

    }, (message) => {
      ElMessage.success(message)
      router.push('/homepage')

    })
  }

}



</script>

<template>
  <!--登录界面设计-->
  <div style="text-align: center; width: 300px;margin: 300px 20px 20px 1050px">

    <!--       登录行设计-->
    <div style="margin-top: 100px;font-size: 14px" >
      <!-- 用户账号/邮箱输入区，用户输入用户名后Enter回车切换到密码行
      加入@keydown.enter.native事件，$refs.passwordInput.focus()将焦点切换到密码框-->
      <el-input v-model="form.username" type="text" placeholder="用户名/邮箱"
                @keydown.enter.native="$refs.passwordInput.focus()">
<!--        用户图标-->
        <template #prefix>
          <el-icon slot="prefix"><User /></el-icon>
        </template>
      </el-input>
      <!--          用户密码输入区, 判断用户输入密码后Enter回车实现登录功能-->
      <el-input ref="passwordInput" v-model="form.password" type="password"
                style="margin-top: 20px" placeholder="密码"
                @keyup.enter.native="login()">
<!--        密码图标-->
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
          <el-checkbox style="color: white" v-model="form.remember" label="记住我"/>
        </el-col>
        <!--           忘记密码请求-->
        <el-col :span="12" style="text-align: right">
          <el-link class="ftp_style" >忘记密码</el-link>


        </el-col>
      </el-row>

    </div>
    <!--        按钮区域-->
    <div style="margin-top: 40px" >

      <el-button  @click ="login()" style="width: 150px" type="success" color="#00FF7F" plain>立即登录</el-button>
    </div>
    <el-divider>
      <span style="color:#DC143C;font-size: 13px ">没有账号</span>
    </el-divider>

    <div>
      <el-button @click="router.push('/register')" style="width: 150px" type="warning" color="#FF8C00" plain>注册账号</el-button>
    </div>


  </div>

</template>


<style scoped>
/*忘记密码：文字链接样式设计*/
.ftp_style{
      color: white;
}
/* 鼠标放在文字链接上颜色改变*/
.ftp_style:hover{
      color: #00FFFF;

}

</style>