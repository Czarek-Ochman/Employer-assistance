import React, {FunctionComponent, useEffect, useState} from 'react';
import "./employee.scss";
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select, {SelectChangeEvent} from '@mui/material/Select';
import InputLabel from '@mui/material/InputLabel';
import {TextField} from "@mui/material";
// import AlertPopup from "../../Component/AlertPopup";
import AlertWithOption from "../../Component/AlertWithOption";
import api from "../../Api/ApiService";

// function createData(
//     name: string,
//     department: string,
//     salary: number,
//     l4days: number,
//     vacationDays: number,
//     id: number,
// ) {
//     return { name, department, salary, l4days, vacationDays, id};
// }
//
// const rows = [
//     createData('Cezary Ochman', "IT", 3500, 1, 12, 1),
//     createData('Aleksander Nowak', "IT", 3410, 5, 0, 2),
//     createData('Paulina Kuc', "HR", 2900, 14, 6 , 3),
//     createData('Adam Nowak', "IT", 3700, 7, 4, 4),
//     createData('Szymon Pietryga', "IT", 3000, 2, 21, 5),
//     createData('Cezary Ochman', "IT", 3500, 1, 12, 1),
//     createData('Aleksander Nowak', "IT", 3410, 5, 0, 2),
//     createData('Paulina Kuc', "HR", 2900, 14, 6 , 3),
//     createData('Adam Nowak', "IT", 3700, 7, 4, 4),
//     createData('Szymon Pietryga', "IT", 3000, 2, 21, 5),
//     createData('Cezary Ochman', "IT", 3500, 1, 12, 1),
//     createData('Aleksander Nowak', "IT", 3410, 5, 0, 2),
//     createData('Paulina Kuc', "HR", 2900, 14, 6 , 3),
//     createData('Adam Nowak', "IT", 3700, 7, 4, 4),
//     createData('Szymon Pietryga', "IT", 3000, 2, 21, 5),
// ];

type Props = {};
export const Employee: FunctionComponent<Props> = (props: Props) => {
    const [isCompany, setIsCompany] = useState(true);
    const [isLoad, setIsLoad] = useState(false);
    const [addEmployee, setAddEmployee] = useState(false);
    const [age, setAge] = React.useState('');
    const [firstName, setFirstName] = React.useState('');
    const [id, setId] = React.useState('');
    const [alert, setAlert] = useState(false);
    const [employeeData, setEmployeeData] = useState<Array<{ name: string, department: string, salary: number, l4days: number, vacationDays: number, id: number }>>([]);


    if (isCompany && !isLoad) {
        api.getAllEmployees().then((res) => {
            const newEmployeeData = res.data.map((employee: any) => createData(
                (employee.person.firstName + ' ' + employee.person.lastName),
                employee.employeeDepartment,
                employee.salary,
                employee.sickDays,
                employee.vacationDays,
                employee.id
            ));
            setEmployeeData(newEmployeeData);
            setIsLoad(true);
        });
    }

    const deleteEmployee = async (id: any) => {
        const result = await api.deleteEmployee(id);
        if (result) {
            setIsLoad(false);
        }
        ;
    }

    function createData(
        name: string,
        department: string,
        salary: number,
        l4days: number,
        vacationDays: number,
        id: number,
    ): { name: string, department: string, salary: number, l4days: number, vacationDays: number, id: number } {
        return {name, department, salary, l4days, vacationDays, id};
    }

    useEffect(() => {
        const fetchData = async () => {
            const result = await api.isCompany();
            setIsCompany(result);
        }
        fetchData();
    }, []);

    const handleChange = (event: SelectChangeEvent) => {
        setAge(event.target.value as string);
    };

    const showAlert = (name: any, id: any) => {
        setAlert(!alert)
        setFirstName(name)
        setId(id);
    };

    const handleEmployee = () => {
        setAddEmployee(!addEmployee);
    };

    return (
        <div className={"employee"}>
            {/*<span onClick={xxx}>klik</span>*/}
            {!isCompany && <div>
                <div className={"employee-text"}>
                    Nie posiadasz dodanej firmy!
                </div>
            </div>}


            {isCompany && <div>
                <div className={"add-employee"}>
                    <button onClick={handleEmployee} className={"add-employee-button"}>Dodaj nowego pracownika</button>
                </div>
                {addEmployee && <div className={"employee-box"}>
                    <div className={"employee-add-box"}>
                        <div className={"employee-text"}>
                            Dodaj nowego pracownika:
                        </div>
                        <div>
                            <div className={"employee-item"}><TextField id="standard-basic" label="Imie"
                                                                        variant="outlined"/></div>
                            <div className={"employee-item"}><TextField id="standard-basic" label="Nazwisko"
                                                                        variant="outlined"/></div>
                            <div className={"employee-item"}><TextField id="standard-basic" label="Wiek"
                                                                        variant="outlined"/></div>
                            <div className={"employee-item"}>
                                <div className={"select"}><FormControl>
                                    <InputLabel id="demo-simple-select-label">Dział</InputLabel>
                                    <Select className={"select-style"}
                                            labelId="demo-simple-select-label"
                                            id="demo-simple-select"
                                            value={age}
                                            label="Age"
                                            onChange={handleChange}
                                    >
                                        <MenuItem value={10}>Ten</MenuItem>
                                        <MenuItem value={20}>Twenty</MenuItem>
                                        <MenuItem value={30}>Thirty</MenuItem>
                                    </Select>
                                </FormControl></div>
                            </div>
                        </div>
                        <div className={"employee-item"}><TextField id="standard-basic" label="Wypłata"
                                                                    variant="outlined"/></div>
                        <div className={"add"}>
                            <button className={"add-button"}>Dodaj</button>
                        </div>
                    </div>
                </div>}
                <TableContainer component={Paper}>
                    <Table sx={{minWidth: 650}} aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell>Imie Nazwisko</TableCell>
                                <TableCell align="right">Dział</TableCell>
                                <TableCell align="right">Pozostałe dni urlopowe</TableCell>
                                <TableCell align="right">Wykorzystane L4</TableCell>
                                <TableCell align="right">Wypłata</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {employeeData.map((row) => (
                                <TableRow
                                    key={row.name}
                                    sx={{'&:last-child td, &:last-child th': {border: 0}}}
                                >
                                    <TableCell component="th" scope="row">
                                        {row.name}
                                    </TableCell>
                                    <TableCell align="right">{row.department}</TableCell>
                                    <TableCell align="right">{row.vacationDays}</TableCell>
                                    <TableCell align="right">{row.l4days}</TableCell>
                                    <TableCell align="right">{row.salary}</TableCell>
                                    <span className={"edit"}><button className={"edit-button"}>Edytuj</button></span>
                                    <span className={"delete"}><button onClick={() => showAlert(row.name, row.id)}
                                                                       className={"delete-button"}>Usuń</button></span>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </div>}
            {alert && <AlertWithOption
                title={'Czy na pewno chcesz skasować pracownika?'}
                message={'Po skasowaniu pracownika  nie będzie możliwości cofnięcia decyzji.' + firstName}
                firstLabel={"Skasuj"}
                secondLabel={"wstecz"}
                onClickFirst={deleteEmployee(id)}
                onClickSecond={showAlert}/>}
        </div>
    );
};