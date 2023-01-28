import axios from "axios";
import api from "./api";
import jwt_decode from "jwt-decode";

interface MyToken {
    "sub": string,
    "id": number,
    "role": string,
    "iat": string,
    "exp": string
}

let config = {
    headerWithAuth: {
        "Content-Type": "application/json",
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Methods': 'POST, GET, OPTIONS, PUT, DELETE',
        Authorization: `Bearer ${localStorage.getItem("accessToken")}`
    },
    headerWithoutAuth: {
        "Content-Type": "application/json",
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Methods': 'POST, GET, OPTIONS, PUT, DELETE',
    },
};

const ApiService = {
    URL: "http://localhost:8080",

    checkHeaderWithAuth() {
        if (config.headerWithAuth.Authorization != `Bearer ${localStorage.getItem("accessToken")}`) {
            config.headerWithAuth.Authorization = `Bearer ${localStorage.getItem("accessToken")}`
        }
    },

    async login(email: string, password: string) {
        return await api.post(`/api/auth/login`, {
            "email": email,
            "password": password
        }, {headers: config.headerWithoutAuth}).then(response => {
            localStorage.setItem("accessToken", response.data.access_token);
            localStorage.setItem("refreshToken", response.data.refresh_token);
            return response
        })
    },

    signUp(email: string, password: string) {
        return api.post(`/api/auth/register`, {
            "email": email,
            "password": password
        }, {headers: config.headerWithoutAuth}).then(response => {
            return response
        })
    },

    isUser() {
        let token = localStorage.getItem("accessToken") || "";
        if (token) {
            return true
        } else {
            return false
        }
    },

    isCompany() {
        let token = localStorage.getItem("accessToken") || "";
        let decodedToken = jwt_decode<MyToken>(token);
        this.checkHeaderWithAuth()
        return api.get(`/api/control-panel/company/user/` + decodedToken.id, {headers: config.headerWithAuth}).then(response => {
            return response.data;
        }).then(data => {
            return data;
        })
    },

    getCompany() {
        let token = localStorage.getItem("accessToken") || "";
        let decodedToken = jwt_decode<MyToken>(token);
        this.checkHeaderWithAuth()
        return api.get(`/api/control-panel/get/company/user/` + decodedToken.id, {headers: config.headerWithAuth}).then(response => {
            return response;
        })
    },

    getAllEmployees() {
        let token = localStorage.getItem("accessToken") || "";
        let decodedToken = jwt_decode<MyToken>(token);
        this.checkHeaderWithAuth()
        return api.get(`/api/control-panel/employee/company`, {headers: config.headerWithAuth}).then(response => {
            return response;
        })
    },

    deleteEmployee(id: any) {
        this.checkHeaderWithAuth()
        return api.delete(`/api/control-panel/employee/`+ id, {headers: config.headerWithAuth}).then(response => {
            if (response.status === 200) {
                return true;
            } else {
                return false;
            }
        });
    },
}

export default ApiService;