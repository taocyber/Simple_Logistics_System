import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router'
import 'element-plus/dist/index.css'
//导入 axios
import axios from "axios";
//挂载axios
axios.defaults.baseURL = 'http://localhost:8080'

const app = createApp(App)

app.use(createPinia())
app.use(router)

app.mount('#app')
