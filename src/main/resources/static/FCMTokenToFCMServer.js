
// Fcm Server 로 Value 전소 ㅇ
const postOrder = async(params) => {

    const res = await axios.post(`http://localhost:8080/api/v1/notification`,params)

    return res.data

}

// Server 로 Token Update 
const postUpdateTokenValue = async(params) => {

    const res = await axios.post(`http://localhost:8080/api/`, params)

    return res.data;
}