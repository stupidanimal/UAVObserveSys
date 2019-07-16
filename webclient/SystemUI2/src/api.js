import axios from 'axios'


export const host = 'http://127.0.0.1:60888/'

axios.defaults.withCredentials = true
axios.defaults.headers = {
    'Content-Type': 'application/x-www-form-urlencoded'
}

export const getLineCodeCollection = () => {
    let url = `${host}/lineCodeCollection`
    return axios.get(url)
}


