import * as React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import Salary from '../../Component/Shared/Assets/salary.jpg';

export default function SalaryCard() {
    return (
        <Card sx={{ maxWidth: 345 }}>
            <CardMedia
                component="img"
                height="140"
                image={Salary}
                alt="kasa"
            />
            <CardContent>
                <Typography gutterBottom variant="h5" component="div">
                    Nagradzanie
                </Typography>
                <Typography variant="body2" color="text.secondary">
                    System umożliwiający nagradzanie pracowników
                </Typography>
            </CardContent>
        </Card>
    );
}