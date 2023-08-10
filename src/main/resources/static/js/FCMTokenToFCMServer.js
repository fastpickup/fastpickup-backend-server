
// Fcm Server 로 Value 전송 
const postOrder = async (message) => {
    console.log('메시지 본문: ',message)
    const res = await axios.post(`http://localhost:8080/api/v1/notification`, message)
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