import axios from 'axios'


export const host = 'http://127.0.0.1:60888/'

axios.defaults.withCredentials = false
axios.defaults.headers = {
    // 'Content-Type': 'application/x-www-form-urlencoded'
    // 'Content-Type': 'application/x-www-form-urlencoded'
    // 'Access-Control-Allow-Origin': '*',
    // 'Content-Type': 'text/plain'
}

export const getLineCodeCollection = () => {
    let url = `${host}lineCodeCollection`
    return axios.get(url)
}

export const getBalloonDataByBalloonCode = (balloonCode) => {
    let url = `${host}balloon?balloonCode=${balloonCode}`
    return axios.get(url)
}


export const getShipLocation = (lineCode) => {
    let url = `${host}ship_location?lineCode=${lineCode}`;
    return axios.get(url)
}


export const getBalloonByDateRange = (dateStartStr, dateEndStr) => {
    let url = `${host}getBalloonByDateRange?dateStartStr=${dateStartStr}&dateEndStr=${dateEndStr}`
    return axios.get(url)
}
