import { createRouter, createWebHistory } from 'vue-router'
import LoginPage from "@/components/welcome/LoginPage.vue";
import RegisterPage from "@/components/welcome/RegisterPage.vue";
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
      {
        path:'/',
        name:'Welcome',
        component:() =>import('@/views/WelcomeVue.vue'),
        children:[
            {
              path:'',
              name:'welcome-login',
              components:{
                  login:LoginPage
              }

        },{
                path:'register',
                name:'welcome-register',
                components:{
                    register:RegisterPage
                }

            }


        ]

  }, {
        path:'/index',
        name:'index',
        component:() =>import('@/views/IndexView.vue')
    }

  ]
})

export default router
