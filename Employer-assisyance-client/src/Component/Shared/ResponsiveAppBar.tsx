import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Menu from '@mui/material/Menu';
import MenuIcon from '@mui/icons-material/Menu';
import Container from '@mui/material/Container';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import Tooltip from '@mui/material/Tooltip';
import MenuItem from '@mui/material/MenuItem';
import AdbIcon from '@mui/icons-material/Adb';
import {useEffect} from "react";
import api from "../../Api/ApiService";

function ResponsiveAppBar() {
    const [anchorElNav, setAnchorElNav] = React.useState<null | HTMLElement>(null);
    const [anchorElUser, setAnchorElUser] = React.useState<null | HTMLElement>(null);
    const [pages, setPages] = React.useState<string[]>(['Logowanie', 'Rejestracja']);
    const [userPages, setUserPages] = React.useState<string[]>(['Panel kontrolny', 'Wyloguj']);
    const [reloaded, setReloaded] = React.useState(false);
    const handleOpenNavMenu = (event: React.MouseEvent<HTMLElement>) => {
        setAnchorElNav(event.currentTarget);
    };

    if (!reloaded) {
        const windowInterval: number = window.setInterval(() => {
            if (localStorage.getItem("accessToken")) {
                setReloaded(true)
            } else {
                console.log("no")
            }
        }, 500);
    }

    const handleOpenUserMenu = (event: React.MouseEvent<HTMLElement>) => {
        setAnchorElUser(event.currentTarget);
    };

    const handleCloseNavMenu = () => {
        setAnchorElNav(null);
    };

    const logout = () => {
        localStorage.removeItem("accessToken");
        localStorage.removeItem("refreshToken");
        window.location.reload();
    }

    const handleCloseUserMenu = () => {
        setAnchorElUser(null);
    };

    // localStorage.getItem("accessToken")

    useEffect(() => {
        if (localStorage.getItem("accessToken")) {
            setReloaded(true)
        }
    }, [localStorage.getItem("accessToken"), reloaded]);

    return (
        <AppBar position="static" style={{background: '#2E3B55'}}>
            <Container maxWidth="xl">
                <Toolbar disableGutters>
                    <Typography
                        variant="h6"
                        noWrap
                        component="a"
                        href="/"
                        sx={{
                            mr: 2,
                            display: {xs: 'none', md: 'flex'},
                            fontFamily: 'monospace',
                            fontWeight: 700,
                            letterSpacing: '.3rem',
                            color: 'inherit',
                            textDecoration: 'none',
                        }}
                    >
                        Employer Assistance
                    </Typography>

                    <Box sx={{flexGrow: 1, display: {xs: 'flex', md: 'none'}}}>
                        <IconButton
                            size="large"
                            aria-label="account of current user"
                            aria-controls="menu-appbar"
                            aria-haspopup="true"
                            onClick={handleOpenNavMenu}
                            color="inherit"
                        >
                            <MenuIcon/>
                        </IconButton>
                        <Menu
                            id="menu-appbar"
                            anchorEl={anchorElNav}
                            anchorOrigin={{
                                vertical: 'bottom',
                                horizontal: 'left',
                            }}
                            keepMounted
                            transformOrigin={{
                                vertical: 'top',
                                horizontal: 'left',
                            }}
                            open={Boolean(anchorElNav)}
                            onClose={handleCloseNavMenu}
                            sx={{
                                display: {xs: 'block', md: 'none'},
                            }}
                        >
                            {pages.map((page) => (
                                <MenuItem key={page} onClick={handleCloseNavMenu}>
                                    <Typography textAlign="center">{page}</Typography>
                                </MenuItem>
                            ))}
                        </Menu>
                    </Box>
                    {/*<AdbIcon sx={{ display: { xs: 'flex', md: 'none' }, mr: 1 }} />*/}
                    {/*<Typography*/}
                    {/*    variant="h5"*/}
                    {/*    noWrap*/}
                    {/*    component="a"*/}
                    {/*    href=""*/}
                    {/*    sx={{*/}
                    {/*        mr: 2,*/}
                    {/*        display: { xs: 'flex', md: 'none' },*/}
                    {/*        flexGrow: 1,*/}
                    {/*        fontFamily: 'monospace',*/}
                    {/*        fontWeight: 700,*/}
                    {/*        letterSpacing: '.3rem',*/}
                    {/*        color: 'inherit',*/}
                    {/*        textDecoration: 'none',*/}
                    {/*    }}*/}
                    {/*>*/}
                    {/*    LOGO*/}
                    {/*</Typography>*/}
                    <Box sx={{flexGrow: 1}}>
                    </Box>
                    {!reloaded &&
                        <Box sx={{flexGrow: 0, display: {xs: 'none', md: 'flex'}}}>
                            <Button
                                href={"/login"}
                                key={""}
                                onClick={handleCloseNavMenu}
                                sx={{my: 3, color: 'white', display: 'block'}}
                            >
                                {"Logowanie"}
                            </Button>
                            <Button
                                href={"/rejestracja"}
                                key={""}
                                onClick={handleCloseNavMenu}
                                sx={{my: 3, color: 'white', display: 'block'}}
                            >
                                {"Rejestracja"}
                            </Button>
                        </Box>}
                    {reloaded &&
                        <Box sx={{flexGrow: 0, display: {xs: 'none', md: 'flex'}}}>
                            <Button
                                href={"/control-panel"}
                                key={""}
                                onClick={handleCloseNavMenu}
                                sx={{my: 3, color: 'white', display: 'block'}}
                            >
                                {"Panel kontrolny"}
                            </Button>
                            <Button
                                href={""}
                                key={""}
                                onClick={logout}
                                sx={{my: 3, color: 'white', display: 'block'}}
                            >
                                {"Wyloguj"}
                            </Button>
                        </Box>}
                </Toolbar>
            </Container>
        </AppBar>
    );
}

export default ResponsiveAppBar;