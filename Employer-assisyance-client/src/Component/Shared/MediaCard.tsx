import * as React from 'react';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Workers from '../../Component/Shared/Assets/workers.jpeg';
import MenuItem from "@mui/material/MenuItem";

const settings = ['Profile', 'Account', 'Dashboard', 'Logout'];

export default function MediaCard() {
    return (
        <Card sx={{ maxWidth: 345 }}>
            <CardMedia
                component="img"
                height="140"
                image={Workers}
                alt="biuro"
            />
            <CardContent>
                <Typography gutterBottom variant="h5" component="div">
                    Kadra
                </Typography>
                <Typography variant="body2" color="text.secondary">
                    Możliwość zatrudnienia lub zwolnienia pracownika.
                </Typography>
            </CardContent>
        </Card>
    );
}