<template>
  <div>
    <a-row type="flex" justify="start" style="margin: 0 0 15px 0;">
      <a-col :span="24" style="text-align: left;">
        <a-space>
          <a-form-model layout="inline">
            <a-form-model-item>
              <a-form-item label="角色">
                <a-input v-model="role" style="width: 300px" placeholder="角色">
                  <a-icon slot="prefix" type="shopping-cart"/>
                </a-input>
              </a-form-item>
            </a-form-model-item>
          </a-form-model>
          <a-form-model layout="inline">
            <a-form-model-item>
              <a-form-item label="权限">
                <a-input disabled="" style="width: 300px" placeholder="权限">
                  <a-icon slot="prefix" type="area-chart"/>
                </a-input>
              </a-form-item>
            </a-form-model-item>
          </a-form-model>
          <a-button @click="getRole" type="primary">精确查询</a-button>
          <a-button disabled="">模糊查询</a-button>
        </a-space>
      </a-col>
    </a-row>

    <a-row>
      <a-col :span="24" style=";min-height: 55vh;text-align: center">
        <a-table :columns="co" :data-source="list" :rowKey="(record,index)=>{return index}" bordered>
          <a slot="name" slot-scope="text">{{ text }}</a>
        </a-table>
      </a-col>
    </a-row>
  </div>
</template>

<script>
const columns = [
  {title: '用户id', dataIndex: 'id', key: 'id'},
  {title: '用户名', dataIndex: 'username', key: 'username'},
  {title: '密码', dataIndex: 'password', key: 'password'},
  {title: '角色', dataIndex: 'role', key: 'role'},
  {title: '权限', dataIndex: 'permission', key: 'permission'}
]

export default {
  name: "DataList",
  data() {
    return {
      role: '',
      co: columns,
      list: []
    }
  },
  methods: {
    getRole() {
      let _this = this
      this.$axios({
        url: 'http://localhost:80/roles',
        params: {
          role: _this.role
        }
      }).then(res => {
        console.log(res)
        _this.list = res.data
      })
    }
  }
}
</script>

<style scoped>

</style>