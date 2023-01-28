import axios from "axios";
import {useEffect, useState} from "react";

const URL = "http://localhost:8080";

export default axios.create({
    method: 'post',
    baseURL: URL,
});