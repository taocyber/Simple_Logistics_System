<script setup>
import {EditPen, Lock, Message, User} from "@element-plus/icons-vue";
import router from "@/router";
import {reactive, ref} from "vue";
import {ElMessage} from "element-plus";
import {post} from "@/net";


//表单内容绑定
const form =reactive( {
      username:'',
      password:'',
      password_verify:'',
      email:'',
      code:'',


})

//用户名输入内容校验规则：包含中文、英文或数字，不能包含特殊字符
// 正则表达式：/^[a-zA-Z0-9\u4e00-\u9fa5]+$/
const validateUsername = (rule, value , callback ) => {
  if (value === '') {
    callback(new Error('请输入用户名'))
  } else if(!/^[a-zA-Z0-9\u4e00-\u9fa5]+$/.test(value)){
    callback(new Error('用户名不能包含特殊字符,请示用中文、英文或数字组合'))
  } else {
    callback()
  }

}

//二次密码输入确认
const validatePassword_verify = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请再次输入密码'))
  } else if (value !== form.password) {
    callback(new Error("两次密码不一致！"))
  } else {
    callback()
  }
}

//表单内容校验规则
const rules ={
  //用户名校验规则选项
    username:[
      {validator: validateUsername,trigger: ['blur', 'change']},
      { min: 5, max: 10, message: '长度应该为5-10位', trigger: 'blur' },
    ],
    password:[
      {required:true, message: '请输入密码',trigger: ['blur', 'change']},
      { min: 6, max: 16, message: '密码长度应该为6-16位', trigger: 'blur' },
    ],
    password_verify:[
      {validator: validatePassword_verify,trigger: ['blur', 'change']},
    ],
    email:[
      {required:true, type: 'email', message: '请输入合法的电子邮件地址',
        trigger: ['blur', 'change']}
    ],
    code:[
      {required:true, message: '请输入获取的验证码', trigger: ['blur', 'change']},
    ]

}
//获取函数引用
const formRef=ref()
//获取验证码按钮默认情况下下不可用，
const isEmailvalid =ref(false)
//确认确认电子邮件正确，获取验证码按钮启用
const onValidate = (prop,isvalid) =>{
    if(prop==='email')
        isEmailvalid.value=isvalid

}
//函数判断整个注册表单是否正确，正确向后台发起请求
const register =() =>{
    formRef.value.validate((isvalid) =>{
      if(isvalid){

      }
      else {
        ElMessage.warning('请完整填写上述注册内容')
      }

    })
}
//邮件验证，获取验证码
const validateEmail = () =>{
      post('/api/auth/valid-email',{
        email: form.email
      },(message) =>{
        ElMessage.success(message)
          }

      )
}


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
<!--    绑定事件：确认电子邮件正确，获取验证码按钮启用-->
    <el-form :model="form" :rules="rules" @validate="onValidate"
              ref="formRef">

      <!--    用户名称, element提供表单校验-->
      <el-form-item prop="username">
        <el-input v-model="form.username" type="text" placeholder="用户名">
          <template #prefix>
            <el-icon slot="prefix"><User /></el-icon>
          </template>
        </el-input>
      </el-form-item>

      <!--        密码设置,element提供表单校验-->
      <el-form-item prop="password">
        <el-input v-model="form.password" type="password" placeholder="密码">
          <template #prefix>
            <el-icon><Lock /></el-icon>
          </template>
        </el-input>
      </el-form-item>

      <!--            确认密码,element提供表单校验-->
      <el-form-item prop="password_verify">
        <el-input v-model="form.password_verify" type="password" placeholder="密码确认">
          <template #prefix>
            <el-icon><Lock /></el-icon>
          </template>
        </el-input>
      </el-form-item>

      <!--    输入邮箱,element-plus提供表单校验-->
      <el-form-item prop="email">
        <el-input v-model="form.email" type="email" placeholder="请输入电子邮箱">
          <template #prefix>
            <el-icon><Message /></el-icon>
          </template>
        </el-input>
      </el-form-item>

      <!--  邮箱验证,element-plus提供表单校验-->

      <el-form-item prop="code">
        <el-row gutter="40" >
          <!--         邮箱输入-->
          <el-col span="18" style="width: 180px">
            <el-input v-model="form.code" type="text" placeholder="请输入验证码">
              <template #prefix>
                <el-icon><EditPen /></el-icon>
              </template>
            </el-input>
          </el-col>
          <!--        ’获取验证码‘按钮,-->
          <el-col span="6" >
            <el-button type="success" @click="validateEmail" :disabled="!isEmailvalid" style="width: 80px">获取验证码</el-button>
          </el-col>
        </el-row>
      </el-form-item>

    </el-form>




  </div>

<!--  注册按钮-->
  <div style="margin-top: 50px;margin-right: 90px">
      <el-button @click="register" style="width: 200px;color: #00FA9A" type="warning" plain>立即注册</el-button>
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