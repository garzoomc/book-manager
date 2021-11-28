<template>
  <div id="index">

    <el-container style="height: 900px; border: 1px solid #eee">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu router :default-active="$route.path" :default-openeds="['0','1']">
            <el-submenu v-for="(item,index) in $router.options.routes" :index="index+''">
              <template slot="title"><i class="el-icon-setting"></i>{{item.name}}</template>
              <el-menu-item v-for="(subItem,subIndex) in item.children" :index="subItem.path"
              :class="$route.path==subItem.path?'is-active':''">{{subItem.name}}</el-menu-item>
            </el-submenu>
        </el-menu>
      </el-aside>

        <el-main>
          <router-view></router-view>
        </el-main>
    </el-container>
  </div>
</template>

<style>
.el-aside {
  color: #333;
}
</style>

<script>
export default {
  name: "Index",
};

</script>