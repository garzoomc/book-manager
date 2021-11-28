import Vue from 'vue'
import VueRouter from 'vue-router'
import PageOne from '../views/Check'
import PageTwo from '../views/Add'
import PageThree from '../views/PageThree'
import PageFour from '../views/PageFour'
import Index from '../views/Index'

Vue.use(VueRouter)
const router = new VueRouter({

  mode:'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path:"/",
      name:"图书管理",
      component:Index,
      redirect: "/pageOne",
      children:[
        {
          path:"pageOne",
          name:"查询图书",
          component:PageOne
        },
        {
          path:"pageTwo",
          name:"添加图书",
          component:PageTwo
        }
      ]
    },
    // {
    //   path:"/",
    //   name:"导航2",
    //   component:Index,
    //   children:[
    //     {
    //       path:"pageThree",
    //       name:"页面3",
    //       component:PageThree
    //     },
    //     {
    //       path:"pageFour",
    //       name:"页面4",
    //       component:PageFour
    //     }
    //   ]
    // }
  ]
})

export default router
