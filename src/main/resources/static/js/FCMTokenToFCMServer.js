
// Fcm Server 로 Value 전송 
const postOrder = async (params) => {

    const res = await axios.post(`http://localhost:8080/api/v1/notification`, params)

    return res.data

}

// Server 로 Token Update 
const postUpdateTokenValue = async (newToken, email) => {
    console.log(newToken, email)
    const params = {
        fcmToken: newToken,
        email: email
    };
    console.log(params)
    const res = await axios.post(`http://localhost:8080/api/fcm/token`, params)

    return res.data;
}