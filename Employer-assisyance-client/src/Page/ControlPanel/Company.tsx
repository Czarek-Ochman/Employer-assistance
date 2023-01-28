import React, {FunctionComponent, useEffect, useState} from 'react';
import "./company.scss";
import {Button, TextField} from "@mui/material";
import api from "../../Api/ApiService";


type Props = {};
export const Company: FunctionComponent<Props> = (props: Props) => {
    const [isCompany, setIsCompany] = useState(false);
    const [companyName, setCompanyName] = useState('');
    const [street, setStreet] = useState('');
    const [city, setCity] = useState('');
    const [zipCode, setZipCode] = useState('');
    const [state, setState] = useState('');
    const [employeeListSize, setEmployeeListSize] = useState(0);

    if (isCompany) {
        api.getCompany().then(res => {
            setCompanyName(res.data.name)
            setStreet(res.data.address.street)
            setCity(res.data.address.city)
            setZipCode(res.data.address.zipCode)
            setState(res.data.address.state)
            api.getAllEmployees().then(res => {
                setEmployeeListSize(res.data.length)
            })

        })
    }

    useEffect(() => {
        const fetchData = async () => {
            const result = await api.isCompany();
            setIsCompany(result);
        }
        fetchData();
    }, []);

    return (
        <div className={"company"}>
            {!isCompany && <div>
                <div>
                    <div className={"company-text"}>
                        Nie posiadasz żadnej firmy - Dodaj ją teraz!
                    </div>
                    <form>
                        <div className={"input-style"}><TextField id="standard-basic" label="Nazwa firmy"
                                                                  variant="outlined"/></div>
                        <div className={"input-style"}><TextField id="standard-basic" label="Państwo"
                                                                  variant="outlined"/></div>
                        <div className={"input-style"}><TextField id="standard-basic" label="Miasto"
                                                                  variant="outlined"/></div>
                        <div className={"input-style"}><TextField id="standard-basic" label="Ulica" variant="outlined"/>
                        </div>
                        <div className={"input-style"}><TextField id="standard-basic" label="Kod pocztowy"
                                                                  variant="outlined"/></div>
                        <button type="button">Dodaj</button>
                    </form>
                </div>
            </div>}
            {isCompany && <div>
                <div>
                    <div className={"company-text"}>
                        Twoja firma: {companyName}</div>
                    <form>
                        <div className={"input-style"}><TextField id="standard-basic" value={"Nazwa: " +companyName}
                                                                  disabled={true} variant="outlined"/></div>
                        <div className={"input-style"}><TextField id="standard-basic" value={"Państwo: " +state} disabled={true}
                                                                  variant="outlined"/></div>
                        <div className={"input-style"}><TextField id="standard-basic" value={"Miasto: " +city} disabled={true}
                                                                  variant="outlined"/></div>
                        <div className={"input-style"}><TextField id="standard-basic" value={"Ulica: " +street} disabled={true}
                                                                  variant="outlined"/></div>
                        <div className={"input-style"}><TextField id="standard-basic" value={"Kod pocztowy: " +zipCode} disabled={true}
                                                                  variant="outlined"/></div>
                        <div className={"input-style"}><TextField id="standard-basic" value={"Ilość pracowników: " +employeeListSize} disabled={true}
                                                                  variant="outlined"/></div>
                    </form>
                </div>
            </div>}
        </div>
    );
};