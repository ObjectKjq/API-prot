import request from '@/utils/request'

// 搜索接口
export function listInterfaceInfoByPage(interfaceInfoQueryRequest) {
    return request({
        url: '/interfaceInfo/list/page',
        method: 'get',
        params:interfaceInfoQueryRequest,
    })
}

// 添加接口
export function addInterfaceInfo(interfaceInfoAddRequest) {
    return request({
        url: '/interfaceInfo/add',
        method: 'post',
        data:interfaceInfoAddRequest,
    })
}

// 修改接口
export function updateInterfaceInfo(interfaceInfoUpdateRequest) {
    return request({
        url: '/interfaceInfo/update',
        method: 'post',
        data:interfaceInfoUpdateRequest,
    })
}

// 删除接口
export function deleteInterfaceInfo(deleteRequest) {
    return request({
        url: '/interfaceInfo/delete',
        method: 'post',
        data:deleteRequest,
    })
}

// 上线接口
export function onlineInterfaceInfo(idRequest) {
    return request({
        url: '/interfaceInfo/online',
        method: 'post',
        data:idRequest,
    })
}

// 下线接口
export function offlineInterfaceInfo(idRequest) {
    return request({
        url: '/interfaceInfo/offline',
        method: 'post',
        data:idRequest,
    })
}

// 调用接口
export function invokeInterfaceInfo(interfaceInfoInvokeRequest) {
    return request({
        url: '/interfaceInfo/invoke',
        method: 'post',
        data:interfaceInfoInvokeRequest,
    })
}