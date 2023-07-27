<script setup>
import { ref, reactive } from 'vue';

const tableData = reactive([
  {
    number: '0001',
    name: '张三',
    gender: '男',
    age: '18',
    data: '2023-6-15',
    contact: '13332123111',
    position: '司机',
    department: '运输部门'
  },
  // 其他员工数据
]);

const dialogVisible = ref(false);
const currentPersonnel = reactive({});

const showDetail = (personnel) => {
  currentPersonnel.number = personnel.number;
  currentPersonnel.name = personnel.name;
  currentPersonnel.gender = personnel.gender;
  currentPersonnel.data = personnel.data;
  currentPersonnel.position= personnel.position;
  currentPersonnel.age = personnel.age;
  currentPersonnel.contact = personnel.contact;
  currentPersonnel.department = personnel.department;
  dialogVisible.value = true;
};

function redact(row) {
  // 编辑员工数据
}

function expurgate(row) {
  // 删除员工数据
}

const addPersonnel = () => {
// 在表格中新增一行数据，同时关闭新增员工数据弹窗
  const newPerson = {
    number: newPersonnel.number,
    name: newPersonnel.name,
    gender: newPersonnel.gender,
    data: newPersonnel.data,
    position: newPersonnel.position,
    age: newPersonnel.age,
    contact: newPersonnel.contact,
    department: newPersonnel.department,
  };
  tableData.push(newPerson);
  addDialogVisible.value = false;
// 清空新增员工数据表单
  newPersonnel.number = '';
  newPersonnel.name = '';
  newPersonnel.gender = '';
  newPersonnel.age = '';
  newPersonnel.data = '';
  newPersonnel.contact = '';
  newPersonnel.position = '';
  newPersonnel.department = '';
};

// 新增员工数据取消
const addCancel = () => {
// 关闭新增员工数据弹窗，并清空新增员工数据表单
  addDialogVisible.value = false;
  newPersonnel.number = '';
  newPersonnel.name = '';
  newPersonnel.gender = '';
  newPersonnel.age = '';
  newPersonnel.data = '';
  newPersonnel.contact = '';
  newPersonnel.position = '';
  newPersonnel.department = '';
};
</script>


<template>


  <el-table :data="tableData" height="600" style="width: 100%">
    <el-table-column prop="number" label="工号"  />
    <el-table-column prop="name" label="姓名"  />
    <el-table-column prop="gender" label="性别"  />
    <el-table-column prop="data" label="入职日期"  />
    <el-table-column prop="position" label="职位" />
    <el-table-column fixed="right" label="操作" width="300">
      <template #default="{ row }">
        <el-button link type="primary" size="large" @click="showDetail(row)" round>查看</el-button>
        <el-button link type="warning" size="large" @click="redact(row)" round>编辑</el-button>
        <el-button link type="danger" size="large" @click="expurgate(row)" round>删除</el-button>
      </template>
      <template #header>
        <el-button type="primary" round style="float: right;margin-right: 20px"
                   @click="addPersonnel">新增</el-button>
      </template>
    </el-table-column>
  </el-table>

  <el-dialog>



  </el-dialog>

  <el-dialog
      v-if="dialogVisible"
      :visible.sync="dialogVisible"
      :title="currentPersonnel.name + ' 的详情'"
      width="30%"
  >
    <el-form>
      <el-form-item label="工号：">{{ currentPersonnel.number }}</el-form-item>
      <el-form-item label="姓名：">{{ currentPersonnel.name }}</el-form-item>
      <el-form-item label="性别：">{{ currentPersonnel.gender }}</el-form-item>
      <el-form-item v-if="dialogVisible" label="年龄：">
        <el-input v-model="currentPersonnel.age" disabled></el-input>
      </el-form-item>
      <el-form-item label="入职日期：">{{ currentPersonnel.data }}</el-form-item>
      <el-form-item v-if="dialogVisible" label="联系方式：">
        <el-input v-model="currentPersonnel.contact" disabled></el-input>
      </el-form-item>
      <el-form-item label="职位：">{{ currentPersonnel.position }}</el-form-item>
      <el-form-item v-if="dialogVisible" label="所属部门：">
        <el-input v-model="currentPersonnel.department" disabled></el-input>
      </el-form-item>

    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogVisible = false">关闭</el-button>
    </div>
  </el-dialog>





</template>






<style scoped>



</style>
