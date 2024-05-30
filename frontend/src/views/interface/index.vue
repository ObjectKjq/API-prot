<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input
        v-model="interfaceInfoQueryRequest.name"
        placeholder="接口名"
        style="width: 200px"
        class="filter-item"
        @keyup.enter.native="handleFilter"
      />
      <el-button
        class="filter-item"
        type="primary"
        icon="el-icon-search"
        @click="handleFilter"
      >
        搜索
      </el-button>
      <el-button
        class="filter-item"
        style="margin-left: 10px"
        type="success"
        icon="el-icon-edit"
        @click="handleCreate"
      >
        添加
      </el-button>
    </div>
    <el-table
      ref="multipleTable"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      stripe
      style="width: 100%"
    >
      <el-table-column prop="id" label="ID" width="170"> </el-table-column>
      <el-table-column prop="name" label="接口名称"> </el-table-column>
      <el-table-column prop="description" label="描述"> </el-table-column>
       <el-table-column prop="requestParams" label="请求参数"></el-table-column>
      <el-table-column prop="method" label="请求方式"></el-table-column>
      <el-table-column prop="url" label="URL"></el-table-column>
      <el-table-column prop="requestHeader" label="请求头"></el-table-column>
      <el-table-column prop="responseHeader" label="响应头"></el-table-column>
      <el-table-column prop="status" label="状态">
        <template slot-scope="scope">
          <span>{{ scope.row.status == 0 ? '已下线' : '已上线' }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" width="98">
        <template slot-scope="scope">
          <span>{{ UTCTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" width="98">
        <template slot-scope="scope">
          <span>{{ UTCTime(scope.row.updateTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="220">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="primary"
            @click="handleEdit(scope.$index, scope.row)"
          >
            编辑
          </el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">
            删除
          </el-button>
          <el-button
            v-if="scope.row.status == 0"
            size="mini"
            type="success"
            @click="onlineInterface(scope.$index, scope.row.id)">
            上线
          </el-button>
          <el-button
            v-if="scope.row.status == 1"
            size="mini"
            type="warning"
            @click="offlineInterface(scope.$index, scope.row.id)">
            下线
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      @current-change="handleCurrentChange"
      :current-page="1"
      :page-size="interfaceInfoQueryRequest.pageSize"
      background
      layout="total, prev, pager, next, jumper"
      :total="total"
      class="page"
    >
    </el-pagination>

    <!-- 弹出一个from表单 -->
    <el-dialog :title="title" :visible.sync="dialogFormVisible" center>
      <el-form :model="formUpdateInster">
        <el-form-item label="接口名称" :label-width="formLabelWidth">
          <el-input v-model="formUpdateInster.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="描述" :label-width="formLabelWidth">
          <el-input type="textarea" v-model="formUpdateInster.description" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="请求方式" :label-width="formLabelWidth">
          <el-input v-model="formUpdateInster.method" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="URL" :label-width="formLabelWidth">
          <el-input v-model="formUpdateInster.url" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="请求头" :label-width="formLabelWidth">
          <el-input type="textarea" v-model="formUpdateInster.requestHeader" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="响应头" :label-width="formLabelWidth">
          <el-input type="textarea" v-model="formUpdateInster.responseHeader" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="interfaceInterUpdate">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { 
  listInterfaceInfoByPage, 
  addInterfaceInfo, 
  updateInterfaceInfo, 
  deleteInterfaceInfo,
  onlineInterfaceInfo,
  offlineInterfaceInfo
} from "@/api/interface";
import { UTCTime } from "@/utils/UTCTime";
export default {
  data() {
    return {
      interfaceInfoQueryRequest: {
        // 第几页
        current: 1,
        // 每页多少条数据
        pageSize: 2,
        // 根据接口名搜
        name: undefined,
      },
      // 总共有多少条数据
      total: 0,
      // 列表是否在加载中
      listLoading: false,
      // 数据绑定到这里
      list: [],
      // 控制表单显示与隐藏
      dialogFormVisible: false,
      // 表单中的数据为空
      formUpdateInster:{},
      formLabelWidth: '120px',
      // 弹窗标题
      title:'',
      // 添加还是修改
      isInster: true
    };
  },
  methods: {
    // 正真执行添加或修改的方法
    async interfaceInterUpdate (){
      // 添加接口
      if(this.isInster){
        const res = await addInterfaceInfo(this.formUpdateInster)
        if(res.code === 20000){
          this.$message({
            type:'success',
            message: '添加成功'
          })
          this.formUpdateInster.id = res.data;
          this.list.unshift(this.formUpdateInster)
        }
      // 修改接口
      }else{
        const res = await updateInterfaceInfo(this.formUpdateInster)
        if(res.code === 20000){
          this.$message({
            type:'success',
            message: '修改成功'
          })
        }
        this.isInster = true;
      }
      this.dialogFormVisible = false;
      this.title = '';
    },
    // 添加用户
    handleCreate() {
      this.formUpdateInster = {};
      this.dialogFormVisible = true;
      this.isInster = true;
      this.title = '添加接口';
    },
    // 编辑信息
    handleEdit(_, ifc) {
      this.dialogFormVisible = true;
      this.isInster = false;
      this.title = '编辑接口';
      this.formUpdateInster = ifc;
    },
    // 删除数据
    handleDelete(index, interfaceInfo) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
          center: true
        }).then(async () => {
          const res = await deleteInterfaceInfo({
            id: interfaceInfo.id
          })
          if(res.code === 20000){
            this.list.splice(index, 1);
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
          }
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
    },
    // 根据用户名搜索数据
    handleFilter() {
      this.listPageInterface();
    },
    // 上线接口
    async onlineInterface(index, id){
      const res = await onlineInterfaceInfo({
        id,
      })
      if(res.code === 20000){
        this.list[index].status = 1;
        this.$message({
          type:'success',
          message: '上线成功'
        })
      }
    },
    // 下线接口
    async offlineInterface(index, id){
      const res = await offlineInterfaceInfo({
        id
      })
      if(res.code === 20000){
        this.list[index].status = 0;
        this.$message({
          type:'success',
          message: '下线成功'
        })
      }
    },
    // 选择页面调用
    handleCurrentChange(current) {
      this.interfaceInfoQueryRequest.current = current;
      this.listPageInterface();
    },
    // 导入处理日期的方法
    ...{ UTCTime },
    // 分页搜索方法
    listPageInterface(){
      this.listLoading = true;
      listInterfaceInfoByPage(this.interfaceInfoQueryRequest).then((res) => {
        this.list = res.data.records;
        this.total = res.data.total
      });
      this.listLoading = false;
    }
  },
  created() {
    this.listPageInterface();
  },
};
</script>

<style scoped>
.page {
  margin-top: 18px;
}
.filter-container{
  margin-bottom: 18px;
}
</style>