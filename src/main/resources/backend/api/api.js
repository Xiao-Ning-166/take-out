
// get 请求
function getApi(url, params) {
    return $axios({
        url: url,
        method: 'get',
        data: params
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
