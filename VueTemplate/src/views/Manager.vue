<template>
  <a-layout id="components-layout-demo-side" style="min-height: 100vh">
    <a-layout-sider v-model="collapsed" collapsible>
      <div class="logo" />
      <a-menu theme="dark" :default-selected-keys="['1']" mode="inline">
        <a-sub-menu key="sub1">
          <span slot="title"><a-icon type="user" /><span>用户管理</span></span>
          <a-menu-item key="3">
            <router-link to="/userinfo">个人信息</router-link>
          </a-menu-item>
          <a-menu-item key="4">
            <router-link to="/userupdate">修改个人信息</router-link>
          </a-menu-item>
        </a-sub-menu>
        <a-sub-menu key="sub2">
          <span slot="title"><a-icon type="team" /><span>数据管理</span></span>
          <a-menu-item key="5">
            <router-link to="/datamodify">数据操作</router-link>
          </a-menu-item>
          <a-menu-item key="6">
            <router-link to="/datalist">数据列表</router-link>
          </a-menu-item>
        </a-sub-menu>
      </a-menu>
    </a-layout-sider>
    <a-layout>
      <a-layout-header style="background: #fff; padding: 5px 0 5px 45px;height: 90px;text-align: right" >
        <a-dropdown>
          <a class="ant-dropdown-link" @click="e => e.preventDefault()">
            Hover me <a-icon type="down" />
          </a>
          <a-menu slot="overlay">
            <a-menu-item>
              <a href="javascript:;">1st menu item</a>
            </a-menu-item>
            <a-menu-item>
              <a href="javascript:;">2nd menu item</a>
            </a-menu-item>
            <a-menu-item>
              <a href="javascript:;">3rd menu item</a>
            </a-menu-item>
          </a-menu>
        </a-dropdown>
      </a-layout-header>
      <a-layout-content style="margin: 0 16px">
        <a-breadcrumb style="margin: 16px 0">
          <a-breadcrumb-item>User</a-breadcrumb-item>
          <a-breadcrumb-item>Bill</a-breadcrumb-item>
        </a-breadcrumb>
        <div :style="{ padding: '20px', background: '#fff', minHeight: '540px' }">
          <router-view/>
        </div>
      </a-layout-content>
      <a-layout-footer style="text-align: center">
        Ant Design ©2018 Created by Ant UED
      </a-layout-footer>
    </a-layout>
  </a-layout>
</template>

<script>
export default {
  name: "Manager",
  data() {
    return {
      collapsed: false,
    }
  },
  methods: {
    onMsg(e) {
      console.log(e.target.checked)
    }
  }
}
</script>

<style scoped>
#components-layout-demo-side .logo {
  height: 32px;
  background: rgba(255, 255, 255, 0.2);
  margin: 16px;
}
</style>