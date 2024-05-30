<template>
  <div class="dashboard-container">
    <el-divider content-position="left">请求结果</el-divider>
    <el-card shadow="never">
      <span>{{requestRes.length >= 0 && requestRes != ''?requestRes:'暂无请求结果'}}</span>
    </el-card>
   
    <el-table
      :data="list"
      v-loading="listLoading"
      @expand-change="initParams"
      style="width: 100%">
      <el-table-column type="expand" class="details">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="接口状态">
              <span>{{ props.row.status==0?'关闭':'打开' }}</span>
            </el-form-item>
            <el-form-item label="接口名称">
              <span>{{ props.row.name }}</span>
            </el-form-item>
            <el-form-item label="描述">
              <span>{{ props.row.description }}</span>
            </el-form-item>
            <el-form-item label="请求地址">
              <span>{{ props.row.url }}</span>
            </el-form-item>
            <el-form-item label="请求方式">
              <span>{{ props.row.method }}</span>
            </el-form-item>
            <el-form-item label="请求参数">
              <span>{{ props.row.requestParams }}</span>
            </el-form-item>
            <el-form-item label="请求头">
              <span>{{ props.row.requestHeader }}</span>
            </el-form-item>
            <el-form-item label="响应头">
              <span>{{ props.row.responseHeader }}</span>
            </el-form-item>
            <el-form-item label="创建时间">
              <span>{{ UTCTime(props.row.createTime) }}</span>
            </el-form-item>
            <el-form-item label="更新时间">
              <span>{{ UTCTime(props.row.updateTime) }}</span>
            </el-form-item>
            <el-divider content-position="left">请求参数</el-divider>
            <el-form-item>
              <el-input type="textarea" style="width:1000px;" v-model="params"></el-input>
            </el-form-item>
            <el-form-item style="margin-top: 20px" >
              <el-button type="primary" @click="onCall(props.row.id)">调用</el-button>
            </el-form-item>
            
        
          </el-form>
        </template>
      </el-table-column>
      <el-table-column
        label="接口名称"
        prop="name"
        width="200">
      </el-table-column>
      <el-table-column
        label="描述"
        prop="description">
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
    
  </div>
</template>

<script>
import { 
  listInterfaceInfoByPage,
  invokeInterfaceInfo
} from "@/api/interface";
import { UTCTime } from "@/utils/UTCTime";
export default {
  name: 'Dashboard',
  data() {
    return {
      list: [],
      interfaceInfoQueryRequest: {
        // 第几页
        current: 1,
        // 每页多少条数据
        pageSize: 2,
      },
      // 总共有多少条数据
      total: 0,
      // 列表是否在加载中
      listLoading: false,
      // 请求参数
      params:'',
      // 请求结果
      requestRes:''
    }
  },
  methods: {
    // 重置请求参数
    initParams(){
      this.params = '';
      this.requestRes = '';
    },
    // 调用接口
    async onCall(id){
      const res = await invokeInterfaceInfo({
        id,
        userRequestParams:this.params,
      })
      if(res.code === 20000){
        // console.log(res.data)
        this.requestRes = res.data;
        this.$message({
          type:'success',
          message:'调用成功'
        })
      }
    },
    handleCurrentChange(current){
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
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
}
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 100%;
}
.page {
  margin-top: 18px;
}
.details{
  background: #f0f9eb;
}
</style>
