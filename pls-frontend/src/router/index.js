import { createRouter, createWebHistory } from 'vue-router'
import LoginPage from "@/components/welcome/LoginPage.vue";
import RegisterPage from "@/components/welcome/RegisterPage.vue";
import PersonnelIM from "@/components/information/PersonnelIM.vue";
import VehicleIM from "@/components/information/VehicleIM.vue";
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
        path:'/homepage',
        name:'homepage',
        component:() =>import('@/views/HomePage.vue')
    },{
        path:'/information',
        name:'information',
        component: () =>import('@/views/BasicInformation.vue'),
        children:[

            {
                path: 'PersonnelIM',
                name: 'PersonnelIM',
                component:  PersonnelIM,

            },{

                path: 'VehicleIM',
                name: 'VehicleIM',
                component:  VehicleIM,

            }




        ]





      },




    ]
})

export default router
