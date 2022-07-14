
// get 请求
function getApi(url, params) {
    return $axios({
        url: url,
        method: 'get',
        params: params
    })
}

// post 请求
function postApi(url, params) {
    return $axios({
        url: url,
        method: 'post',
        data: params
    })
}

// put 请求
function putApi(url, params) {
    return $axios({
        url: url,
        method: 'put',
        data: params
    })
}

// delete 请求
function deleteApi(url, params) {
    return $axios({
        url: url,
        method: 'delete',
        params: params
    })
}
