<template>
  <div>
    <a-form-model :model="form" :label-col="labelCol" :wrapper-col="wrapperCol">
      <a-form-model-item label="活动名称">
        <a-input v-model="form.name"/>
      </a-form-model-item>
      <a-form-model-item label="活动区">
        <a-select v-model="form.region" placeholder="请选择您的区域">
          <a-select-option value="上海">
            一区
          </a-select-option>
          <a-select-option value="北京">
            二区
          </a-select-option>
        </a-select>
      </a-form-model-item>
      <a-form-model-item label="活动时间">
        <a-date-picker
            v-model="form.date1"
            show-time
            type="date"
            placeholder="选择一个日期"
            style="width: 100%;"
        />
      </a-form-model-item>
      <a-form-model-item label="即时交付">
        <a-switch v-model="form.delivery"/>
      </a-form-model-item>
      <a-form-model-item label="活动类型">
        <a-checkbox-group v-model="form.type">
          <a-checkbox value="1" name="type">
            在线
          </a-checkbox>
          <a-checkbox value="2" name="type">
            忙碌
          </a-checkbox>
          <a-checkbox value="3" name="type">
            离线
          </a-checkbox>
        </a-checkbox-group>
      </a-form-model-item>
      <a-form-model-item label="资源">
        <a-radio-group v-model="form.resource">
          <a-radio value="1">
            赞助商
          </a-radio>
          <a-radio value="2">
            地点
          </a-radio>
        </a-radio-group>
      </a-form-model-item>
      <a-form-model-item label="活动形式">
        <a-input v-model="form.desc" type="textarea"/>
      </a-form-model-item>
      <a-form-model-item :wrapper-col="{ span: 14, offset: 4 }">
        <a-button type="primary" @click="onSubmit">
          取消
        </a-button>
        <a-button style="margin-left: 10px;">
          取消
        </a-button>
      </a-form-model-item>
    </a-form-model>
  </div>
</template>

<script>
export default {
  name: "UserUpdate",
  data() {
    return {
      labelCol: {span: 4},
      wrapperCol: {span: 14},
      form: {
        name: '',
        region: undefined,
        date1: undefined,
        delivery: false,
        type: [],
        resource: '',
        desc: '',
      },
    };
  },
  methods: {
    onSubmit() {
      console.log('submit!', this.form);
    },
  },
}
</script>

<style scoped>

</style>