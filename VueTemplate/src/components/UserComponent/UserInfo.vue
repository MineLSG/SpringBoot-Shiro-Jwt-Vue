<template>
  <div id="component-page">
    <a-row type="flex" justify="center">
      <a-col :span="12" :style="{width:'450px',height:'450px'}">
        <a-card title="用户信息" style="width: 100%">
          <p>ID: {{ id }}</p>
          <p>用户名: {{ username }}</p>
          <p>密 码: {{ password }}</p>
          <p>角 色: {{ role }}</p>
          <p>权 限: {{ permission }}</p>
        </a-card>
      </a-col>
    </a-row>
  </div>
</template>

<script>
export default {
  beforeRouteEnter(to, from, next) {
    if (sessionStorage.getItem('uuid') !== '') {
      if (to.path === '/userinfo') {
        next()
      }
    } else {
      this.$router.push("/")
    }
  },
  name: "UserInfo",
  created() {
    let _this = this
    const token = sessionStorage.getItem('token')
    this.$axios({
      url: 'http://localhost:80/msg',
      headers: {
        'token': token
      }
    }).then(res => {
      console.log(res)
      _this.id = res.data.message.id
      _this.username = res.data.message.username
      _this.password = res.data.message.password
      _this.role = res.data.message.role
      _this.permission = res.data.message.permission
    })
  },
  data() {
    return {
      id: '',
      username: '',
      password: '',
      role: '',
      permission: ''
    }
  },
}
</script>

<style scoped>
#component-page {
  margin: 25px;
}
</style>