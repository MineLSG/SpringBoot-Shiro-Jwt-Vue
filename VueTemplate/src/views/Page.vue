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
  name: "Page",
  beforeRouteEnter(to, from, next) {
    if (sessionStorage.getItem('uuid') !== '') {
      if (to.path === '/page') {
        next()
      }
    } else {
      this.$router.push("/")
    }
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
  created() {
    let _this = this
    const uuid = sessionStorage.getItem('uuid').split('&')
    this.$axios({
      url: 'http://localhost:80/msg',
      headers: {
        'App_id': uuid[0],
        'Auth_id': uuid[1]
      }
    }).then(res => {
      console.log(res)
      _this.id = res.data.message.id
      _this.username = res.data.message.username
      _this.password = res.data.message.password
      _this.role = res.data.message.role
      _this.permission = res.data.message.permission
    })
  }
}
</script>

<style scoped>
#component-page {
  margin: 25px;
}
</style>