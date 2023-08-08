const postOrder = async(params) => {

    const res = await axios.post(`http://localhost:8080/api/v1/notification`,params)

    return res.data

}
